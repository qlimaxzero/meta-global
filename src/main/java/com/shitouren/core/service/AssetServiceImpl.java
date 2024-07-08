package com.shitouren.core.service;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shitouren.core.aop.Lock;
import com.shitouren.core.autogenerate.bean.Collection;
import com.shitouren.core.autogenerate.bean.HideRecord;
import com.shitouren.core.autogenerate.bean.MyOrder;
import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.autogenerate.dao.CollectionDao;
import com.shitouren.core.bean.eums.AssetRecordEnum;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.bean.eums.OrderStatusEnum;
import com.shitouren.core.bean.eums.RedDotEnum;
import com.shitouren.core.common.Constants;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.pay.local.LocalPayClient;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.model.dto.RedDotEventDTO;
import com.shitouren.core.model.vo.PayVO;
import com.shitouren.core.model.vo.RecordVO;
import com.shitouren.core.model.vo.TrialCalcVO;
import com.shitouren.core.mp.bo.*;
import com.shitouren.core.mp.mapper.*;
import com.shitouren.core.service.processor.HideRecordProcessor;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.DecimalUtil;
import com.shitouren.core.utils.DictConfigResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetSaleMapper assetSaleMapper;
    private final AssetUserMapper assetUserMapper;
    private final AssetRecordMapper assetRecordMapper;
    private final AssetFrozenMapper assetFrozenMapper;
    private final ExchangeMapper exchangeMapper;
    private final CollectionDao collectionDao;

    private final DictService dictService;
    private final OrderService orderService;
    private final UserService userService;
    private final LocalPayClient localPayClient;
    private final HideRecordProcessor hideRecordProcessor;

    private final TaskExecutor taskExecutor;
    private final CloudRedisTemplate cloudRedisTemplate;
    private final ApplicationEventPublisher eventPublisher;;

    private static final Map<Integer, String> saleRatioMap = new HashMap<>();

    @PostConstruct
    public void init() {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(() -> {
            saleRatioMap.putAll(assetSaleMapper.selectList(null).stream().collect(Collectors.toMap(AssetSale::getId, AssetSale::getPayRatio)));
        }, 0, 10, TimeUnit.MINUTES);
    }

    @Override
    public List<AssetUser> getUserAssets(Integer uid) {
        LambdaQueryWrapper<AssetUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetUser::getUserid, uid);
        return assetUserMapper.selectList(queryWrapper);
    }

    @Override
    public List<RecordVO> getUserAssetRecords(Integer uid, AssetTypeEnum asset) {
        cloudRedisTemplate.hdel(Constants.RECORD_REDDOT_KEY + uid, RedDotEnum.ASSET.name());
        LambdaQueryWrapper<AssetRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(asset != null, AssetRecord::getAsset, asset);
        queryWrapper.eq(AssetRecord::getUserid, uid);
        queryWrapper.orderByDesc(AssetRecord::getId);
        List<AssetRecord> assetRecords = assetRecordMapper.selectList(queryWrapper);
        return assetRecords.stream().map(x -> {
            String desc = "";
            String suffix = "";
            if (StrUtil.isNotBlank(x.getRelatedIds())) {
                int t = x.getType().intValue();
                if (Objects.equals(AssetRecordEnum.BLINDBOX_BUY.getType(), t)) {
                    MyOrder order = orderService.getById(Integer.parseInt(x.getRelatedIds()));
                    AssertUtil.notNull(order, ExceptionConstant.参数异常);
                    suffix = orderService.getByIssueId(order.getIssueId()).getName();
                    Collection collection = collectionDao.selectByPrimaryKey(order.getCollid());
                    desc = collection.getName() + " * " + order.getGrants();
                }
                if (AssetRecordEnum.equalsAny(t, AssetRecordEnum.MARKET_BUY, AssetRecordEnum.MARKET_SELL)) {
                    HideRecord record = hideRecordProcessor.getHideRecord(Integer.parseInt(x.getRelatedIds()));
                    AssertUtil.notNull(record, ExceptionConstant.参数异常);
                    desc = collectionDao.selectByPrimaryKey(record.getCollid()).getName();
                }
                if (AssetRecordEnum.equalsAny(t, AssetRecordEnum.TRANSFER_SEND, AssetRecordEnum.TRANSFER_RECEIVE)) {
                    Users user = userService.getUserById(Integer.parseInt(x.getRelatedIds()));
                    AssertUtil.notNull(user, ExceptionConstant.参数异常);
                    desc = user.getUserId() + " " + user.getNickName();
                }
            }
            return RecordVO.builder()
                    .id(x.getId())
                    .createTime(x.getCreateTime())
                    .assetName(x.getAsset())
                    .assetAmt(x.getAmt())
                    .type(x.getType().intValue())
                    .typeName(AssetRecordEnum.getDescByType(x.getType()) + " " + suffix)
                    .desc(desc)
                    .build();
        }).collect(Collectors.toList());
    }

    @Override
    public Map<String, List<AssetSale>> getAssetSales() {
        LambdaQueryWrapper<AssetSale> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetSale::getStatus, Constants.ASSET_SALE_UP);
        queryWrapper.orderByAsc(AssetSale::getSort, AssetSale::getId);
        List<AssetSale> assetSales = assetSaleMapper.selectList(queryWrapper);
        return assetSales.stream().collect(Collectors.groupingBy(AssetSale::getAsset));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void miningRewardGrant2Frozen(Integer uid, AssetTypeEnum asset, BigDecimal frozenAmt, AssetRecordEnum recordEnum) {
        // 调整用户冻结资产
        modifyFrozenAmt(uid, asset, frozenAmt, recordEnum);
        // 新增冻结记录
        long frozenDuration = Long.parseLong(dictService.getValue(DictConst.ASSET_FROZEN_DURATION));
        assetFrozenMapper.save(uid, asset, frozenAmt, Constants.ASSET_STATUS_FROZEN, frozenDuration);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyUsableAmt(Integer uid, AssetTypeEnum asset, BigDecimal amt, AssetRecordEnum optType, String relatedIds) {
        log.info("start >>> uid {} asset {} optType {} optAmt {} relatedIds {}", uid, asset, optType, amt, relatedIds);
        AssetUser assetUser = assetUserMapper.getAssetUser(uid, asset);
        if (assetUser == null && DecimalUtil.gt0(amt)) {
            assetUser = assetUserMapper.initAsset(uid, asset);
        }
        AssertUtil.notNull(assetUser, ExceptionConstant.余额不足);
        BigDecimal oldUsableAmt = DecimalUtil.of(assetUser.getUsableAmt());
        AssertUtil.isTrue(DecimalUtil.gt0(amt) || oldUsableAmt.compareTo(amt.abs()) >= 0, ExceptionConstant.余额不足);
        assetUser.setTotalAmt(DecimalUtil.add(assetUser.getTotalAmt(), amt));
        assetUser.setUsableAmt(DecimalUtil.add(assetUser.getUsableAmt(), amt));
        boolean rs = assetUserMapper.updateAmtByCAS(assetUser, oldUsableAmt, assetUser.getFrozenAmt());
        log.info("end <<< uid {} asset {} optType {} optAmt {} oldUsableAmt {} usableAmt {}  rs {}", uid, asset, optType, amt, oldUsableAmt, assetUser.getUsableAmt(), rs);
        AssertUtil.isTrue(rs, ExceptionConstant.更新失败);
        assetRecordMapper.save(uid, asset, optType, amt, assetUser.getTotalAmt(), relatedIds);
        eventPublisher.publishEvent(new RedDotEventDTO(uid, RedDotEnum.ASSET));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modifyFrozenAmt(Integer uid, AssetTypeEnum asset, BigDecimal frozenAmt, AssetRecordEnum optType) {
        boolean isFrozen = Objects.equals(optType, AssetRecordEnum.MINING_FROZEN);
        boolean isUnfrozen = Objects.equals(optType, AssetRecordEnum.MINING_UNFROZEN);
        AssertUtil.isTrue(isFrozen || isUnfrozen, ExceptionConstant.参数异常);

        log.info("start >>> uid {} asset {} optType {} frozenAmt {}", uid, asset, optType, frozenAmt);
        AssetUser assetUser = assetUserMapper.getAssetUser(uid, asset);
        if (assetUser == null && isFrozen) {
            assetUser = assetUserMapper.initAsset(uid, asset);
        }
        AssertUtil.notNull(assetUser, ExceptionConstant.余额不足);
        BigDecimal oldUsableAmt = assetUser.getUsableAmt();
        BigDecimal oldFrozenAmt = assetUser.getFrozenAmt();
        if (isFrozen) {
            assetUser.setTotalAmt(DecimalUtil.add(assetUser.getTotalAmt(), frozenAmt));
            assetUser.setFrozenAmt(DecimalUtil.add(assetUser.getFrozenAmt(), frozenAmt));
        } else {
            assetUser.setUsableAmt(DecimalUtil.add(assetUser.getUsableAmt(), frozenAmt));
            assetUser.setFrozenAmt(DecimalUtil.sub(assetUser.getFrozenAmt(), frozenAmt));
        }
        boolean rs = assetUserMapper.updateAmtByCAS(assetUser, oldUsableAmt, oldFrozenAmt);
        log.info("end <<< uid {} asset {} optType {} frozenAmt {} oldFrozenAmt {} usableAmt {} oldUsableAmt {} rs {}", uid, asset, optType, frozenAmt, oldFrozenAmt, assetUser.getUsableAmt(), oldUsableAmt, rs);
        AssertUtil.isTrue(rs, ExceptionConstant.更新失败);
        // 新增资产变动记录
        assetRecordMapper.save(uid, asset, optType, frozenAmt, assetUser.getTotalAmt(), null);
        eventPublisher.publishEvent(new RedDotEventDTO(uid, RedDotEnum.ASSET));
    }

    @Override
    public void unfrozenAmtTask() {
        LambdaQueryWrapper<AssetFrozen> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetFrozen::getStatus, Constants.ASSET_STATUS_FROZEN);
        queryWrapper.lt(AssetFrozen::getUnfrozenTime, LocalDateTime.now());
        List<AssetFrozen> frozenList = assetFrozenMapper.selectList(queryWrapper);
        log.info("frozenList.size={}", frozenList.size());
        AssetService assetService = (AssetService) AopContext.currentProxy();
        frozenList.forEach(x -> {
            assetService.modifyFrozenAmt(x.getUserid(), AssetTypeEnum.valueOf(x.getAsset()), x.getFrozenAmt(), AssetRecordEnum.MINING_UNFROZEN);
            AssertUtil.isTrue(assetFrozenMapper.unfrozenById(x), ExceptionConstant.更新失败);
        });
    }

    @Override
    public PayVO exchangeTrialCalc(Integer uid, Integer saleId, AssetTypeEnum asset, BigDecimal payAmt) {
        Triple<AssetSale, BigDecimal, BigDecimal> triple = checkAssetSale(uid, saleId, asset, payAmt);
        return PayVO.builder().payAmt(triple.getMiddle()).assetAmt(triple.getRight()).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PayVO exchangeConfirm(Integer uid, Integer saleId, AssetTypeEnum asset, BigDecimal payAmt) {
        AssetService assetService = (AssetService) AopContext.currentProxy();
        Triple<ExchangeRecord, BigDecimal, BigDecimal> triple = assetService.deductExchangeStock(uid, saleId, asset, payAmt);

        String toAddr;
        payAmt = triple.getMiddle();
        try {
            toAddr = localPayClient.getAddress(payAmt);
            AssertUtil.isFalse(exchangeMapper.selectExistsRepeatToAddr(toAddr, payAmt, OrderStatusEnum.WAIT), ExceptionConstant.EXCHANGE_ADJUST_AMT);
            String txId = localPayClient.create(toAddr, payAmt);
            boolean rs = exchangeMapper.updateById(triple.getLeft().getId(), toAddr, txId);
            log.info("uid {} asset {} payAmt {} toAddr {} txId {} rs {}", uid, asset, payAmt, toAddr, txId, rs);
            AssertUtil.isTrue(rs, ExceptionConstant.更新失败);
        } catch (Exception e) {
            log.error("uid {} err {}", uid, e.getMessage());
            assetService.handleExchangeComplete(triple.getLeft(), true);
            throw e;
        }

        int countDown = Integer.parseInt(dictService.getValue(DictConst.EXCHANGE_COUNTDOWN, Constants.EXPIRE_TIME));
        return PayVO.builder()
                .exchangeId(triple.getLeft().getId())
                .expireTime(DateUtil.offsetMinute(new Date(), countDown).toString())
                .toAddr(toAddr)
                .payAmt(payAmt)
                .assetAmt(triple.getRight())
                .build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Lock(prefix = Constants.EXCHANGE_LOCK_KEY, key = "#saleId")
    public Triple<ExchangeRecord, BigDecimal, BigDecimal> deductExchangeStock(Integer uid, Integer saleId, AssetTypeEnum asset, BigDecimal payAmt) {
        Triple<AssetSale, BigDecimal, BigDecimal> triple = checkAssetSale(uid, saleId, asset, payAmt);
        AssetSale assetSale = triple.getLeft();
        BigDecimal dueAssetAmt = triple.getRight();
        //payAmt = triple.getMiddle();
        ExchangeRecord exchangeRecord = exchangeMapper.createPayingRecord(uid, assetSale.getId(), DictConst.PAY_TYPE_U, triple.getMiddle(), asset, dueAssetAmt);
        boolean rs = assetSaleMapper.updateSoldByCAS(assetSale.getId(), DecimalUtil.add(assetSale.getSold(), dueAssetAmt), assetSale.getSold());
        log.info("uid {} asset {} payAmt {} sold {} dueAssetAmt {} exchangeId {} rs {}",
                uid, asset, payAmt, assetSale.getSold(), dueAssetAmt, exchangeRecord != null ? exchangeRecord.getId() : "", rs);
        AssertUtil.isTrue(rs && exchangeRecord != null, ExceptionConstant.更新失败);
        eventPublisher.publishEvent(new RedDotEventDTO(uid, RedDotEnum.EXCHANGE));
        return Triple.of(exchangeRecord, triple.getMiddle(), dueAssetAmt);
    }

    private Triple<AssetSale, BigDecimal, BigDecimal> checkAssetSale(Integer uid, Integer saleId, AssetTypeEnum asset, BigDecimal tmpPayAmt) {
        AssertUtil.isTrue(Objects.equals(Constants.SWITCH_ON, dictService.getValue(DictConst.EXCHANGE_SWITCH)), ExceptionConstant.暂未开放);
        BigDecimal payAmt = DecimalUtil.of(tmpPayAmt);
        AssertUtil.isTrue(DecimalUtil.gt0(payAmt), ExceptionConstant.参数异常);
        AssertUtil.isNull(exchangeMapper.getExchange(uid, OrderStatusEnum.WAIT), ExceptionConstant.存在支付中订单);
        AssetSale assetSale = assetSaleMapper.getCurrentAssetSale(asset);
        AssertUtil.notNull(assetSale, ExceptionConstant.ASSET_SOLD_OUT);
        AssertUtil.isTrue(Objects.equals(saleId, assetSale.getId()), ExceptionConstant.参数异常);
        BigDecimal sellableAmt = DecimalUtil.sub(assetSale.getAmt(), assetSale.getSold());
        AssertUtil.isTrue(DecimalUtil.gt0(sellableAmt), ExceptionConstant.ASSET_SOLD_OUT);
        BigDecimal payRatio = DictConfigResolver.getPayRatio(assetSale.getPayRatio());
        BigDecimal dueAssetAmt = DecimalUtil.multiply(payAmt, payRatio);
        AssertUtil.isTrue(DecimalUtil.gt0(dueAssetAmt), ExceptionConstant.参数异常);
        if (dueAssetAmt.compareTo(sellableAmt) > 0) {
            payAmt = DecimalUtil.div(sellableAmt, payRatio);
            dueAssetAmt = sellableAmt;
        }
        // 不足1时赠送
        if (DecimalUtil.sub(sellableAmt, dueAssetAmt).compareTo(BigDecimal.ONE) < 0) {
            dueAssetAmt = sellableAmt;
        }
        AssertUtil.isTrue(assetSale.getQuota() == null || DecimalUtil.eq0(assetSale.getQuota()) || dueAssetAmt.compareTo(assetSale.getQuota()) <= 0, ExceptionConstant.ASSET_QUOTA_LIMIT);
        AssertUtil.isTrue(sellableAmt.compareTo(dueAssetAmt) >= 0, ExceptionConstant.ASSET_INSUFFICIENT);
        return Triple.of(assetSale, payAmt, dueAssetAmt);
    }

    @Override
    public void exchangeComplete(Integer uid) {
        ExchangeRecord record = exchangeMapper.getLastExchange(uid);
        AssertUtil.notNull(record, ExceptionConstant.数据异常);
        AssetService assetService = (AssetService) AopContext.currentProxy();
        assetService.handleExchangeComplete(record, false);
    }

    @Override
    public void exchangeCancel(Integer uid, Integer id) {
        ExchangeRecord record = exchangeMapper.selectById(id);
        AssertUtil.notNull(record, ExceptionConstant.参数异常);
        AssertUtil.isTrue(Objects.equals(record.getUserid(), uid), ExceptionConstant.参数异常);
        AssetService assetService = (AssetService) AopContext.currentProxy();
        assetService.handleExchangeComplete(record, true);
    }

    @Override
    public void exchangeQueryTask() {
        List<ExchangeRecord> records = exchangeMapper.selectRecord(OrderStatusEnum.WAIT);
        log.info("records.size = {}", records.size());

        AssetService assetService = (AssetService) AopContext.currentProxy();
        for (ExchangeRecord record : records) {
            assetService.handleExchangeComplete(record, false);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    @Lock(prefix = Constants.EXCHANGE_LOCK_KEY, key = "#record.assetSaleId")
    public void handleExchangeComplete(ExchangeRecord record, boolean force) {
        LocalDateTime now = LocalDateTime.now();
        // http异常订单 兜底处理
        if (force) {
            dealExchangeCancel(record, now);
            return;
        }

        boolean rs = localPayClient.query(record.getOutOrderNo());
        if (rs) {
            record.setStatus(OrderStatusEnum.COMPLETE.getStatus());
            record.setSuccessTime(now);
            record.setUpdateTime(now);
            exchangeMapper.updateById(record);

            modifyUsableAmt(record.getUserid(), AssetTypeEnum.valueOf(record.getAssetName()), record.getAssetAmt(), AssetRecordEnum.EXCHANGE, String.valueOf(record.getId()));
            eventPublisher.publishEvent(new RedDotEventDTO(record.getUserid(), RedDotEnum.EXCHANGE));
            return;
        }

        long countDown = Long.parseLong(dictService.getValue(DictConst.EXCHANGE_COUNTDOWN, Constants.EXPIRE_TIME));
        if (record.getCreateTime().plusMinutes(countDown).isBefore(now)) {
            dealExchangeCancel(record, now);
        }
    }

    private void dealExchangeCancel(ExchangeRecord record, LocalDateTime now) {
        record.setStatus(OrderStatusEnum.CANCEL.getStatus());
        record.setUpdateTime(now);
        int i = exchangeMapper.updateById(record);
        AssertUtil.isTrue(i == 1, ExceptionConstant.更新失败);

        AssetSale assetSale = assetSaleMapper.selectById(record.getAssetSaleId());
        boolean r = assetSaleMapper.updateSoldByCAS(assetSale.getId(), DecimalUtil.sub(assetSale.getSold(), record.getAssetAmt()), assetSale.getSold());
        AssertUtil.isTrue(r, ExceptionConstant.更新失败);
        eventPublisher.publishEvent(new RedDotEventDTO(record.getUserid(), RedDotEnum.EXCHANGE));
    }

    @Override
    public List<RecordVO> getUserExchangeRecords(Integer uid) {
        cloudRedisTemplate.hdel(Constants.RECORD_REDDOT_KEY + uid, RedDotEnum.EXCHANGE.name());
        LambdaQueryWrapper<ExchangeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExchangeRecord::getUserid, uid);
        queryWrapper.orderByDesc(ExchangeRecord::getId);
        List<ExchangeRecord> records = exchangeMapper.selectList(queryWrapper);
        int countDown = Integer.parseInt(dictService.getValue(DictConst.EXCHANGE_COUNTDOWN, Constants.EXPIRE_TIME));
        return records.stream().map(x ->
            RecordVO.builder()
                    .id(x.getId())
                    .createTime(x.getCreateTime())
                    .expireTime(x.getCreateTime().plusMinutes(countDown))
                    .type(x.getStatus())
                    .typeName(OrderStatusEnum.getDescByType(x.getStatus()))
                    .assetName(x.getAssetName())
                    .assetAmt(x.getAssetAmt())
                    .payType(x.getPayType())
                    .payAmt(x.getPayAmt())
                    .ratio(DictConfigResolver.getPayRatio(saleRatioMap.get(x.getAssetSaleId())).toString())
                    .build()
        ).collect(Collectors.toList());
    }

    @Override
    public RecordVO getUserExchangeDetail(Integer uid, Integer id) {
        ExchangeRecord x = exchangeMapper.selectById(id);
        AssertUtil.notNull(x, ExceptionConstant.参数异常);
        AssertUtil.isTrue(Objects.equals(x.getUserid(), uid), ExceptionConstant.参数异常);
        return RecordVO.builder()
                .id(x.getId())
                .createTime(x.getCreateTime())
                .expireTime(x.getCreateTime().plusMinutes(Integer.parseInt(dictService.getValue(DictConst.EXCHANGE_COUNTDOWN, Constants.EXPIRE_TIME))))
                .type(x.getStatus())
                .typeName(OrderStatusEnum.getDescByType(x.getStatus()))
                .assetName(x.getAssetName())
                .assetAmt(x.getAssetAmt())
                .payType(x.getPayType())
                .payAmt(x.getPayAmt())
                .ratio(DictConfigResolver.getPayRatio(assetSaleMapper.selectById(x.getAssetSaleId()).getPayRatio()).toString())
                .toAddr(x.getToAddr())
                .build();
    }

    @Override
    public Map<String, Boolean> getRedDot(Integer uid) {
        Map<Object, Object> map = cloudRedisTemplate.hmget(Constants.RECORD_REDDOT_KEY + uid);
        Map<String, Boolean> rmap = new HashMap<>();
        rmap.put(RedDotEnum.EXCHANGE.name(), map.get(RedDotEnum.EXCHANGE.name()) != null);
        rmap.put(RedDotEnum.ASSET.name(), map.get(RedDotEnum.ASSET.name()) != null);
        rmap.put(RedDotEnum.NFT.name(), map.get(RedDotEnum.NFT.name()) != null);
        return rmap;
//        ExchangeRecord ex = exchangeMapper.getLastExchange(uid);
//        rmap.put(Constants.RECORD_REDDOT_EXCHANGE, ex != null && !Objects.equals(ex.getId(), map.get(Constants.RECORD_REDDOT_EXCHANGE)));
//
//        AssetRecord ar = assetRecordMapper.getLastAssetRecord(uid);
//        rmap.put(Constants.RECORD_REDDOT_ASSET, ar != null && !Objects.equals(ar.getId(), map.get(Constants.RECORD_REDDOT_ASSET)));
//
//        HideRecord hr = hideRecordProcessor.getLastHideRecord(uid);
//        rmap.put(Constants.RECORD_REDDOT_NFT, hr != null && !Objects.equals(hr.getId(), map.get(Constants.RECORD_REDDOT_NFT)));
    }

    @Override
    public TrialCalcVO trialCalcTransfer(BigDecimal amt, AssetTypeEnum asset) {
        String key = DictConst.TRANSFER_FEE_RATE_PREFIX + asset;
        BigDecimal serviceFeeRate = dictService.getDecimalValue(key);
        BigDecimal serviceFee = DecimalUtil.multiply(amt, serviceFeeRate);
        BigDecimal realAmt = DecimalUtil.add(amt, serviceFee);
        return TrialCalcVO.builder().sellPrice(amt).realPrice(realAmt).serviceFee(serviceFee).serviceFeeRate(serviceFeeRate).build();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void transfer(Integer uid, Integer receiveUid, BigDecimal amt, AssetTypeEnum asset) {
        AssertUtil.isTrue(Objects.equals(Constants.SWITCH_ON, dictService.getValue(DictConst.TRANSFER_SWITCH)), ExceptionConstant.暂未开放);
        AssertUtil.isFalse(Objects.equals(uid, receiveUid), ExceptionConstant.ASSET_TRANSFER_SELF);
        Users user = userService.getUserById(receiveUid);
        AssertUtil.notNull(user, ExceptionConstant.账号不存在);
        String key = DictConst.TRANSFER_FEE_RATE_PREFIX + asset;
        modifyUsableAmt(uid, asset, DecimalUtil.add(amt, DecimalUtil.multiply(amt, dictService.getDecimalValue(key))).negate(), AssetRecordEnum.TRANSFER_SEND, String.valueOf(receiveUid));
        modifyUsableAmt(receiveUid, asset, DecimalUtil.of(amt), AssetRecordEnum.TRANSFER_RECEIVE, String.valueOf(uid));
    }

}
