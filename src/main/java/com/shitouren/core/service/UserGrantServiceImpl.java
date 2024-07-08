package com.shitouren.core.service;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shitouren.core.aop.Lock;
import com.shitouren.core.autogenerate.bean.*;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.dao.*;
import com.shitouren.core.bean.eums.*;
import com.shitouren.core.common.Constants;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.config.sse.SSEClientUtil;
import com.shitouren.core.config.sse.SSEProperties;
import com.shitouren.core.config.sse.SSEServerUtil;
import com.shitouren.core.model.dto.BuyEventDTO;
import com.shitouren.core.model.dto.PowerEventDTO;
import com.shitouren.core.model.dto.NFTHealthDTO;
import com.shitouren.core.model.vo.*;
import com.shitouren.core.mp.bo.DialogueRecord;
import com.shitouren.core.mp.mapper.DialogueRecordMapper;
import com.shitouren.core.mp.mapper.UserGrantMapper;
import com.shitouren.core.service.processor.HideRecordProcessor;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.DateUtils;
import com.shitouren.core.utils.DecimalUtil;
import com.shitouren.core.utils.DictConfigResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserGrantServiceImpl extends ServiceImpl<UserGrantMapper, UserGrant> implements UserGrantService {

    private final UserGrantMapper userGrantMapper;
    private final DialogueRecordMapper dialogueRecordMapper;
    private final UserGrantDao userGrantDao;
    private final UserService userService;
    private final IssueDao issueDao;
    private final BlindboxDao blindboxDao;
    private final CollectionDao collectionDao;
    private final DictService dictService;
    private final AssetService assetService;
    private final OrderService orderService;
    private final SSEProperties sseProperties;
    private final HideRecordProcessor hideRecordProcessor;
    private final CloudRedisTemplate cloudRedisTemplate;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public int countInitNFT(Integer uid) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        queryWrapper.eq(UserGrant::getState, Constants.NFT_STATE_INIT);
        return count(queryWrapper);
    }

    @Override
    public BigDecimal sumTotalPower(Integer uid) {
        return userGrantMapper.sumTotalPower(uid);
    }

    @Override
    public Pair<BigDecimal, BigDecimal> sumPowerByAsset(Integer uid, boolean isTotal) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        queryWrapper.eq(UserGrant::getType, 0);
        List<UserGrant> list = list(queryWrapper);

        String healthConfig = dictService.getValue(DictConst.HEALTH_LEVEL_CHANGE_CONFIG);
        List<Integer> aicNFTIdList = DictConfigResolver.getNFTIdList(dictService.getValue(DictConst.AIC_NFT_ID_LIST));
        Map<Boolean, BigDecimal> map = list.stream().collect(
                Collectors.groupingBy(o -> aicNFTIdList.contains(o.getCollid()),
                        Collectors.mapping(
                                x -> isTotal ? x.getPower() : DecimalUtil.multiply(x.getPower(), DictConfigResolver.getHealthHitPercent(healthConfig, x.getHealth())),
                                Collectors.reducing(BigDecimal.ZERO, DecimalUtil::add)
                        )
                )
        );

        return Pair.of(DecimalUtil.of(map.get(false)), DecimalUtil.of(map.get(true)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Lock(key = "'init:nft:' + #uid")
    public void initGrantNFT(Integer uid, String nickname, Integer gender) {
        AssertUtil.isTrue(Constants.INIT_NFT_LIST.contains(gender), ExceptionConstant.参数异常);
        Integer collId = gender;
        AssertUtil.isFalse(countInitNFT(uid) > 0, ExceptionConstant.重复请求);

        Collection collection = collectionDao.selectByPrimaryKey(collId);
        UserGrant grant = userGrantMapper.addNFT(uid, collId, BigDecimal.ZERO, Constants.NFT_STATE_INIT, collection.getPower(),
                Constants.DISPLAY_ON, dictService.getDecimalValue(DictConst.HEALTH_DEFAULT));

        userService.updateNickname(uid, nickname);
        hideRecordProcessor.add(uid, grant.getId(), NftTransferEnum.GIFTS, grant.getCollid(), null);
    }

    @Override
    public List<NFTVO> userNFTs(Integer uid) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        queryWrapper.orderByDesc(UserGrant::getDisplay, UserGrant::getCollid);
        List<UserGrant> userGrants = userGrantMapper.selectList(queryWrapper);
        if (userGrants.isEmpty()) {
            return ListUtil.empty();
        }

        Map<Integer, Collection> collMap = getCollMapByGrants(userGrants);
        return userGrants.stream().map(x -> {
            Collection collection = collMap.get(x.getCollid());
            return NFTVO.builder()
                    .id(x.getId())
                    .no(x.getTruenumber())
                    .name(collection.getName())
                    .health(x.getHealth())
                    .imgUrl(collection.getImg())
                    .display(x.getDisplay())
                    .build();
        }).collect(Collectors.toList());
    }

    @NotNull
    private Map<Integer, Collection> getCollMapByGrants(List<UserGrant> userGrants) {
        List<Integer> collIds = userGrants.stream()
                .map(UserGrant::getCollid)
                .distinct()
                .collect(Collectors.toList());
        return getCollMapByCollIds(collIds);
    }

    private Map<Integer, Collection> getCollMapByCollIds(List<Integer> collIds) {
        if (collIds == null || collIds.isEmpty()) {
            return Collections.emptyMap();
        }
        CollectionExample ex = new CollectionExample();
        ex.createCriteria().andIdIn(collIds);
        return collectionDao.selectByExample(ex).stream().collect(Collectors.toMap(Collection::getId, Function.identity()));
    }

    @Override
    public List<SellNFTVO> userMarketNFTs(Integer uid, Integer type) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        if (Objects.equals(type, Constants.USER_MARKET_NFT_UP)) {
            queryWrapper.in(UserGrant::getType, ListUtil.of(Constants.NFT_TYPE_UP, Constants.NFT_TYPE_TRADE));
        } else {
            queryWrapper.eq(UserGrant::getType, Constants.NFT_TYPE_INIT);
            queryWrapper.notIn(UserGrant::getCollid, Constants.INIT_NFT_LIST);
        }
        queryWrapper.orderByDesc(UserGrant::getTradeTime);
        List<UserGrant> userGrants = userGrantMapper.selectList(queryWrapper);
        if (userGrants.isEmpty()) {
            return ListUtil.empty();
        }

        Map<Integer, Collection> collMap = getCollMapByGrants(userGrants);
        return userGrants.stream().map(x -> {
            Collection collection = collMap.get(x.getCollid());
            return SellNFTVO.builder()
                    .id(x.getId())
                    .no(x.getTruenumber())
                    .name(collection.getName())
                    .price(x.getSellprice())
                    .imgUrl(collection.getImg())
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public List<UserGrant> selectList(Integer uid) {
        LambdaQueryWrapper<UserGrant> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserGrant::getUserid, uid);
        return userGrantMapper.selectList(queryWrapper);
    }

    @Override
    public TrialCalcHealthVO trialCalcRestoreHealth(Integer uid, Integer id, boolean isInner) {
        BigDecimal healthDefault = dictService.getDecimalValue(DictConst.HEALTH_DEFAULT);
        BigDecimal restoreHealth;
        List<UserGrant> grants = new ArrayList<>();
        if (id != null) {
            UserGrant grant = getById(id);
            AssertUtil.notNull(grant, ExceptionConstant.NFT_NOT_EXIST);
            grants.add(grant);
            restoreHealth = DecimalUtil.sub(healthDefault, grant.getHealth());
        } else {
            grants.addAll(userGrantMapper.selectByUid(uid));
            AssertUtil.isFalse(grants.isEmpty(), ExceptionConstant.NFT_NOT_EXIST);
            restoreHealth = grants.stream()
                    .map(x -> DecimalUtil.sub(healthDefault, x.getHealth()))
                    .reduce(BigDecimal.ZERO, DecimalUtil::add);
        }
        BigDecimal healthRestoreUnitVal = dictService.getDecimalValue(DictConst.HEALTH_RESTORE_UNIT_VAL);
        AssertUtil.isTrue(restoreHealth.compareTo(healthRestoreUnitVal) >= 0, ExceptionConstant.NFT_HEALTH_FULL);

        BigDecimal healthRestoreUnitFee = dictService.getDecimalValue(DictConst.HEALTH_RESTORE_UNIT_FEE);
        BigDecimal restoreFee = DecimalUtil.multiply(DecimalUtil.div(restoreHealth, healthRestoreUnitVal), healthRestoreUnitFee);
        AssetTypeEnum assetUnit = AssetTypeEnum.valueOf(dictService.getValue(DictConst.HEALTH_RESTORE_UNIT_ASSET));
        TrialCalcHealthVO build = TrialCalcHealthVO.builder().health(restoreHealth).fee(restoreFee).asset(assetUnit).num(grants.size()).build();
        if (isInner) {
            build.setGrants(grants);
        }
        return build;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restoreHealth(Integer uid, Integer id) {
        TrialCalcHealthVO calcHealthVO = trialCalcRestoreHealth(uid, id, true);
        String join = StrUtil.join(StrUtil.COMMA, calcHealthVO.getGrants().stream().map(UserGrant::getId).collect(Collectors.toList()));
        assetService.modifyUsableAmt(uid, calcHealthVO.getAsset(), calcHealthVO.getFee().negate(), AssetRecordEnum.RESTORE_HEALTH, join);

        // 恢复
        BigDecimal healthDefault = dictService.getDecimalValue(DictConst.HEALTH_DEFAULT);
        calcHealthVO.getGrants().forEach(x -> {
            updateHealthByCAS(x.getId(), healthDefault, x.getHealth());
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetDisplay(Integer uid, Integer id) {
        UserGrant userGrant = userGrantMapper.getDisplayedGrant(uid);
        AssertUtil.notNull(userGrant, ExceptionConstant.参数异常);
        AssertUtil.isFalse(Objects.equals(userGrant.getId(), id), ExceptionConstant.参数异常);

        userGrant.setDisplay(Constants.DISPLAY_OFF);
        userGrantMapper.updateById(userGrant);

        UserGrant grant = getById(id);
        grant.setDisplay(Constants.DISPLAY_ON);
        userGrantMapper.updateById(grant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateHealthByCAS(Integer id, BigDecimal newHealth, BigDecimal oldHealth) {
        LambdaUpdateWrapper<UserGrant> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.set(UserGrant::getHealth, newHealth);
        updateWrapper.eq(UserGrant::getId, id);
        updateWrapper.eq(UserGrant::getHealth, oldHealth);
        return update(updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void buyMarket(Integer buyer, Integer id) {
        UserGrant userGrant = userGrantDao.selectByPrimaryKey(id);
        AssertUtil.notNull(userGrant, ExceptionConstant.NFT_NOT_EXIST);
        AssertUtil.isTrue(Objects.equals(userGrant.getType(), Constants.NFT_TYPE_UP), ExceptionConstant.NFT_DELISTED);
        Integer seller = userGrant.getUserid();
        AssertUtil.isFalse(Objects.equals(seller, buyer), ExceptionConstant.NFT_IS_OWN);

        String lockKey = Constants.MARKET_LOCK_KEY + id;
        boolean lock = cloudRedisTemplate.getLock(lockKey, lockKey, 5);
        AssertUtil.isTrue(lock, ExceptionConstant.服务器限流);

        BigDecimal sellPrice = userGrant.getSellprice();
        BigDecimal buyFeeRate = dictService.getDecimalValue(DictConst.MARKET_BUY_FEE_RATE);
        BigDecimal payPrice = DecimalUtil.add(sellPrice, DecimalUtil.multiply(sellPrice, buyFeeRate));

        BigDecimal sellFeeRate = dictService.getDecimalValue(DictConst.MARKET_SELL_FEE_RATE);
        BigDecimal receivePrice = DecimalUtil.sub(sellPrice, DecimalUtil.multiply(sellPrice, sellFeeRate));

        Integer transferIdBuy = hideRecordProcessor.add(buyer, id, NftTransferEnum.MARKET_BUY, userGrant.getCollid(), seller);
        Integer transferIdSell = hideRecordProcessor.add(seller, id, NftTransferEnum.MARKET_SELL, userGrant.getCollid(), buyer);

        assetService.modifyUsableAmt(buyer, AssetTypeEnum.AIC, payPrice.negate(), AssetRecordEnum.MARKET_BUY, String.valueOf(transferIdBuy));
        assetService.modifyUsableAmt(seller, AssetTypeEnum.AIC, receivePrice, AssetRecordEnum.MARKET_SELL, String.valueOf(transferIdSell));

        userGrant.setUserid(buyer);
        userGrant.setType(Constants.NFT_TYPE_INIT);
        userGrant.setState(Constants.NFT_STATE_MARKET);
        userGrant.setBuytime(new Date());
        userGrant.setTradeTime(new Date());
        userGrantMapper.updateById(userGrant);

        boolean release = cloudRedisTemplate.releaseLock(lockKey, lockKey);
        AssertUtil.isTrue(release, ExceptionConstant.更新失败);

        eventPublisher.publishEvent(new BuyEventDTO(buyer, id, UserBuyEnum.MARKET));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Lock(key = "'blindbox:' + #id")
    public NFTVO buyBlindBox(Integer buyer, Integer id) {
        Issue issue = issueDao.selectByPrimaryKey(id);
        AssertUtil.notNull(issue, ExceptionConstant.参数异常);
        AssertUtil.isTrue(Objects.equals(issue.getType(), Constants.ISSUE_TYPE_UP), ExceptionConstant.参数异常);
        AssertUtil.isTrue(DateUtil.compare(issue.getReleasetime(), new Date()) < 0, ExceptionConstant.暂未开放);
        AssertUtil.isTrue(DateUtil.compare(issue.getEndtime(), new Date()) > 0, ExceptionConstant.暂未开放);
        AssertUtil.isTrue(issue.getPresale() > issue.getSold(), ExceptionConstant.已售完);

        Blindbox blindbox = blindboxDao.selectByPrimaryKey(issue.getCollid());
        AssertUtil.notNull(blindbox, ExceptionConstant.参数异常);
        Pair<Integer, Integer> pair = DictConfigResolver.getRandomHitCollId(blindbox.getProbably());
        AssertUtil.notNull(pair.getKey(), ExceptionConstant.参数异常);
        Collection coll = collectionDao.selectByPrimaryKey(pair.getKey());
        AssertUtil.notNull(coll, ExceptionConstant.参数异常);

        // 订单&记录
        Integer num = pair.getValue();
        AssetTypeEnum asset = DictConfigResolver.getBlindBoxPayAsset(blindbox.getPaytype());
        BigDecimal healthDefault = dictService.getDecimalValue(DictConst.HEALTH_DEFAULT);
        MyOrder order = orderService.createBlindBoxOrder(buyer, OrderStatusEnum.COMPLETE, blindbox.getPrice(), coll.getId(), issue.getId(), asset.getType(), num);
        for (int i = 0; i < num; i++) {
            UserGrant grant = userGrantMapper.addNFT(buyer, coll.getId(), blindbox.getPrice(), Constants.NFT_STATE_BUY, coll.getPower(), Constants.DISPLAY_OFF, healthDefault);
            hideRecordProcessor.add(buyer, grant.getId(), NftTransferEnum.BLINDBOX_BUY, grant.getCollid(), order.getId());
        }
        assetService.modifyUsableAmt(buyer, asset, blindbox.getPrice().negate(), AssetRecordEnum.BLINDBOX_BUY, String.valueOf(order.getId()));
        // 已售
        int rs = issueDao.updateSoldByCAS(issue.getId(), issue.getSold() + num, issue.getSold());
        AssertUtil.isTrue(rs == 1, ExceptionConstant.更新失败);

        eventPublisher.publishEvent(new PowerEventDTO(buyer, null));
        eventPublisher.publishEvent(new BuyEventDTO(buyer, null, UserBuyEnum.BLIND_BOX));
        return NFTVO.builder().num(num).name(coll.getName()).imgUrl(coll.getImg()).build();
    }

    @Override
    public NFTVO getIndexNFT(Integer uid) {
        UserGrant userGrant = userGrantMapper.getDisplayedGrant(uid);
        AssertUtil.notNull(userGrant, ExceptionConstant.数据异常);

        Integer checkCollId = userGrant.getYuanyuzhouid() > 0 ? userGrant.getYuanyuzhouid() : userGrant.getCollid();
        Collection collection = collectionDao.selectByPrimaryKey(checkCollId);
        AssertUtil.notNull(collection, ExceptionConstant.数据异常);

        Integer useWordCount = cloudRedisTemplate.getInt(Constants.NFT_WORD_COUNT_KEY + userGrant.getId());
        int remainingWordCount = collection.getWordLimit() - useWordCount;

        List<DialogueRecord> list = dialogueRecordMapper.selectLast(uid, userGrant.getId());
        return NFTVO.builder()
                .id(userGrant.getId())
                .name(collection.getName())
                .avatarUrl(collection.getCreatorimg())
                .imgUrl(collection.getImg())
                .no(userGrant.getTruenumber())
                .health(userGrant.getHealth())
                .display(userGrant.getDisplay())
                .wordLimit(collection.getWordLimit())
                .remainingWordCount(Math.max(remainingWordCount, 0))
                .dialogueList(list.stream().map(x -> DialogueRecordVO.builder().q(x.getQ()).a(x.getA()).createTime(x.getCreateTime()).build()).collect(Collectors.toList()))
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRole(Integer uid, String msg) {
        AssertUtil.notBlank(msg, ExceptionConstant.参数异常);
        AssertUtil.isTrue(msg.startsWith(Constants.AI_ROLE_SYMBOL) && msg.length() < 1000, ExceptionConstant.参数异常);
        UserGrant grant = userGrantMapper.getDisplayedGrant(uid);
        AssertUtil.notNull(grant, ExceptionConstant.数据异常);

        log.info("uid {} grantId {} oldRole {} newRole {}", uid, grant.getId(), grant.getDialogueRole(), msg);
        String str = msg.substring(2);
        boolean hasChinese = Validator.hasChinese(str);
        grant.setDialogueRole(str + (hasChinese ? ",使用中文" : ",use English"));
        userGrantMapper.updateById(grant);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SseEmitter dialogue(Integer uid, String msg) {
        log.info("uid {} msg {}", uid, msg);
        SseEmitter emitter = SSEServerUtil.connect(uid);
        try {
            AssertUtil.notBlank(msg, ExceptionConstant.参数异常);
            UserGrant grant = userGrantMapper.getDisplayedGrant(uid);
            AssertUtil.notNull(grant, ExceptionConstant.数据异常);
            BigDecimal healthThreshold = dictService.getDecimalValue(DictConst.NFT_AI_HEALTH_THRESHOLD);
            AssertUtil.isTrue(grant.getHealth().compareTo(healthThreshold) > 0, ExceptionConstant.NFT_HEALTH_LACK);
            if (StrUtil.isBlank(grant.getDialogueId())) {
                grant.setDialogueId(SSEClientUtil.create(sseProperties.getCreateUrl(), grant.getId()));
                userGrantMapper.updateById(grant);
            }

            String role = StrUtil.isBlank(grant.getDialogueRole()) ? collectionDao.selectByPrimaryKey(grant.getCollid()).getIntroduce() : grant.getDialogueRole();
            SSEClientUtil.connect(uid, sseProperties.getStreamUrl(), grant.getDialogueId(), role, msg);
        } catch (CloudException e) {
            log.error("uid {} error {}", uid, e.getMessage());
            Map<String, Object> map = new HashMap<>();
            map.put("code", e.getCode());
            map.put("info", e.getInfo());
            SSEServerUtil.send(true, uid, JSONUtil.toJsonStr(map));
        } catch (Exception e) {
            log.error("uid {}", uid, e);
            Map<String, Object> map = new HashMap<>();
            map.put("code", ExceptionConstant.请求失败.getCode());
            map.put("info", ExceptionConstant.请求失败.getMsg());
            SSEServerUtil.send(true, uid, JSONUtil.toJsonStr(map));
        }
        return emitter;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NFTHealthDTO deductAIHealth(Integer uid, String question, String answer) {
        UserGrant grant = userGrantMapper.getDisplayedGrant(uid);
        AssertUtil.notNull(grant, ExceptionConstant.数据异常);
        Integer wordLimit = collectionDao.selectByPrimaryKey(grant.getCollid()).getWordLimit();
        Integer useWordCount = cloudRedisTemplate.getInt(Constants.NFT_WORD_COUNT_KEY + grant.getId());

        String context = question + answer;
        NFTHealthDTO dto = new NFTHealthDTO();
        int needCalcWordCount = DictConfigResolver.getNeedCalcWordCount(context, useWordCount, wordLimit);
        dto.setRemainingWordCount(needCalcWordCount < 0 ? Math.abs(needCalcWordCount) : 0);
        if (needCalcWordCount <= 0) {
            dto.setStop(false);
            dto.setUseHealth(BigDecimal.ZERO);
        } else {
            String wordLimitCycle = dictService.getValue(DictConst.NFT_AI_WORD_LIMIT);
            BigDecimal healthReduce = dictService.getDecimalValue(DictConst.NFT_AI_HEALTH_REDUCE);
            BigDecimal realHealthReduce = DictConfigResolver.getHealthReduceByWord(needCalcWordCount, wordLimitCycle, healthReduce);
            BigDecimal newHealth = DecimalUtil.sub(grant.getHealth(), realHealthReduce);
            boolean rs = updateHealthByCAS(grant.getId(), newHealth, grant.getHealth());
            AssertUtil.isTrue(rs, ExceptionConstant.数据异常);
            dto.setStop(newHealth.compareTo(dictService.getDecimalValue(DictConst.NFT_AI_HEALTH_THRESHOLD)) <= 0);
            dto.setUseHealth(realHealthReduce);
            log.info("uid {} needCalcWordCount {} realHealthReduce {}", uid, needCalcWordCount, realHealthReduce);
        }

        dialogueRecordMapper.save(uid, grant, question, answer, dto.getUseHealth());
        cloudRedisTemplate.set(Constants.NFT_WORD_COUNT_KEY + grant.getId(), useWordCount + context.length(), DateUtils.getSecondEndOfDay());
        return dto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateHistoryDialogueRecord(Integer uid, Integer nftId) {
        dialogueRecordMapper.deleteList(uid, nftId);
    }

    @Override
    public List<RecordNFTVO> getUserNFTRecords(Integer uid) {
        cloudRedisTemplate.hdel(Constants.RECORD_REDDOT_KEY + uid, RedDotEnum.NFT.name());
        List<HideRecord> list = hideRecordProcessor.selectRecords(uid);
        if (list.isEmpty()) {
            return ListUtil.empty();
        }

        List<Integer> ids = list.stream().map(HideRecord::getCollid).distinct().collect(Collectors.toList());
        Map<Integer, Collection> collectionMap = getCollMapByCollIds(ids);
        return list.stream().map(x ->
                RecordNFTVO.builder()
                        .createTime(LocalDateTimeUtil.of(x.getCreateTime()))
                        .type(x.getType())
                        .typeName(NftTransferEnum.getDescByType(x.getType()))
                        .title(collectionMap.get(x.getCollid()).getName())
                        .desc(userGrantMapper.selectById(x.getUserGrantId()).getTruenumber())
                        .build()
        ).collect(Collectors.toList());
    }

    @Override
    public List<NFTAvatarVO> getHoldAvatar(Integer uid, Integer nftId) {
        UserGrant userGrant = userGrantMapper.selectById(nftId);
        AssertUtil.notNull(userGrant, ExceptionConstant.参数异常);
        AssertUtil.isTrue(Objects.equals(userGrant.getDisplay(), Constants.DISPLAY_ON), ExceptionConstant.参数异常);
        // 填充基础NFT id
        List<Integer> collIds = getFillCollIds(uid);
        Map<Integer, Collection> collMap = getCollMapByCollIds(collIds);
        Set<Map.Entry<Integer, Collection>> entries = collMap.entrySet();
        Map.Entry.comparingByKey();
        Integer checkCollId = userGrant.getYuanyuzhouid() > 0 ? userGrant.getYuanyuzhouid() : userGrant.getCollid();
        return entries.stream()
                .map(x ->
                        NFTAvatarVO.builder()
                                .display(Objects.equals(x.getKey(), checkCollId) ? Constants.DISPLAY_ON : Constants.DISPLAY_OFF)
                                .id(x.getKey())
                                .avatarUrl(x.getValue().getCreatorimg())
                                .imgUrl(x.getValue().getImg())
                                .build()
                ).collect(Collectors.toList());
    }

    @NotNull
    private List<Integer> getFillCollIds(Integer uid) {
        List<Integer> collIds = userGrantMapper.selectCollIdByUid(uid);
        if (collIds.contains(Constants.INIT_NFT_ID_M)) {
            collIds.add(Constants.INIT_NFT_ID_F);
        } else {
            collIds.add(Constants.INIT_NFT_ID_M);
        }
        return collIds;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkHoldAvatar(Integer uid, Integer nftId, Integer collId) {
        List<Integer> collIds = getFillCollIds(uid);
        AssertUtil.isTrue(collIds.contains(collId), ExceptionConstant.参数异常);
        UserGrant userGrant = userGrantMapper.selectById(nftId);
        AssertUtil.notNull(userGrant, ExceptionConstant.参数异常);
        AssertUtil.isTrue(Objects.equals(userGrant.getUserid(), uid), ExceptionConstant.参数异常);
        AssertUtil.isTrue(Objects.equals(userGrant.getDisplay(), Constants.DISPLAY_ON), ExceptionConstant.参数异常);
        userGrant.setYuanyuzhouid(collId);
        userGrantMapper.updateById(userGrant);
    }

    @Override
    public void airdropQueryTask(int sec) {
        List<Integer> uids = userGrantMapper.selectUidByState(Constants.NFT_STATE_AIRDROP, LocalDateTime.now().plusSeconds(-(sec + 3)));//多查3s
        uids.forEach(uid -> eventPublisher.publishEvent(new PowerEventDTO(uid, null)));
    }

}
