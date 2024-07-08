package com.shitouren.core.service;


import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Pair;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.ImmutableMap;
import com.shitouren.core.aop.Lock;
import com.shitouren.core.autogenerate.bean.UserGrant;
import com.shitouren.core.bean.eums.AssetRecordEnum;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.common.Constants;
import com.shitouren.core.common.DictConst;
import com.shitouren.core.mp.bo.Guild;
import com.shitouren.core.utils.DictConfigResolver;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.model.dto.MiningDTO;
import com.shitouren.core.mp.bo.MiningSpeedRecord;
import com.shitouren.core.mp.bo.MiningRecord;
import com.shitouren.core.mp.mapper.*;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.DecimalUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class MiningServiceImpl implements MiningService {

    private final MiningRecordMapper miningRecordMapper;
    private final MiningSpeedRecordMapper miningSpeedRecordMapper;
    private final MiningRewardRecordMapper miningRewardRecordMapper;

    private final GuildService guildService;
    private final UserService userService;
    private final AssetService assetService;
    private final DictService dictService;
    private final UserGrantService userGrantService;

    private final TaskExecutor taskExecutor;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @Lock(key = "'mining:' + #uid")
    public void startMining(Integer uid) {
        Integer countToday = miningRecordMapper.countToday(uid);
        AssertUtil.isTrue(countToday == 0, ExceptionConstant.MINING_ALREAY);

        MiningRecord miningRecord = miningRecordMapper.insert(uid, null, Constants.MINING_STATUS_NOT);
        AssertUtil.notNull(miningRecord, ExceptionConstant.网络异常);
        /*
        // 一级
        Users user = userService.getUserById(uid);
        if (user.getInvitationId() != null) {
            redisTemplate.opsForHash().increment("20230101-1", user.getInvitationId(), 1);
            // 二级
            Users parent = userService.getUserById(user.getInvitationId());
            if (parent.getInvitationId() != null) {
                redisTemplate.opsForHash().increment("20230101-2", parent.getInvitationId(), 1);
            }
        }
        */
    }

    private BigDecimal getMiningSpeedByShowUnit(BigDecimal realPower, String miningUnit) {
        BigDecimal miningUnitDecimal;
        if (Objects.equals("h", miningUnit)) {
            miningUnitDecimal = new BigDecimal("3600");
        } else if (Objects.equals("m", miningUnit)) {
            miningUnitDecimal = new BigDecimal("60");
        } else {
            miningUnitDecimal = BigDecimal.ONE;
        }
        return DecimalUtil.multiply(realPower, miningUnitDecimal);
    }

    /**
     * nft/邀请/健康值变化都会影响到当前挖掘
     * @param isTotal 是否签到: 根据当前用户是否签到进行算力值计算
     *                未签到, 总算力: 计算总应获得算力 (所有用户，不分区是否签到，是否有效)
     *                已签到, 实时算力: 计算实时算力 (已签到且为有效用户，不区分级别)
     */
    @Override
    public MiningDTO getMiningAsset(Integer uid, List<Integer> miningUidList, boolean isTotal) {
        // 总算力: 1.nft*健康值影响 2.邀请 3.公会会长 4.公会排名
        Pair<BigDecimal, BigDecimal> assetPair = userGrantService.sumPowerByAsset(uid, isTotal);
        // 实际基础算力: nftPower * nftHealthPercent
        BigDecimal baseAIBPower = assetPair.getKey();
        BigDecimal baseAICPower = assetPair.getValue();

        // 邀请算力
        // 过滤有效用户
        List<Integer> validMiningUidList = isTotal ? ListUtil.empty() : userService.filterValidUid(miningUidList);
        // 邀请1级
        BigDecimal invitationPercent1 = dictService.getDecimalValue(DictConst.INVITATION_POWER_REWARD_PERCENT1);
        var triple1 = handleInvitationPower(ListUtil.of(uid), validMiningUidList, invitationPercent1, isTotal);
        BigDecimal invitationAIBPower1 = triple1.getMiddle();
        BigDecimal invitationAICPower1 = triple1.getRight();
        // 邀请2级
        BigDecimal invitationPercent2 = dictService.getDecimalValue(DictConst.INVITATION_POWER_REWARD_PERCENT2);
        var triple2 = handleInvitationPower(triple1.getLeft(), validMiningUidList, invitationPercent2, isTotal);
        BigDecimal invitationAIBPower2 = triple2.getMiddle();
        BigDecimal invitationAICPower2 = triple2.getRight();

        // 会长算力
        Pair<BigDecimal, BigDecimal> pairLeader = handleGuildLeaderPower(uid);
        BigDecimal guildLeaderAIBPower = pairLeader.getKey();
        BigDecimal guildLeaderAICPower = pairLeader.getValue();

        // 公会排名算力
        Pair<BigDecimal, BigDecimal> pairRanking = handleGuildRankingPower(uid, baseAIBPower, baseAICPower);
        BigDecimal guildRankingAIBPower = pairRanking.getKey();
        BigDecimal guildRankingAICPower = pairRanking.getValue();

        BigDecimal finalAIBPower = DecimalUtil.add(baseAIBPower, invitationAIBPower1, invitationAIBPower2, guildLeaderAIBPower, guildRankingAIBPower);
        BigDecimal finalAICPower = DecimalUtil.add(baseAICPower, invitationAICPower1, invitationAICPower2, guildLeaderAICPower, guildRankingAICPower);

        BigDecimal miningRateAIB = dictService.getDecimalValue(DictConst.MINING_RATE_AIB);
        BigDecimal nowAIB = DecimalUtil.multiply(finalAIBPower, miningRateAIB);
        BigDecimal nowAIC = DecimalUtil.multiply(finalAICPower, dictService.getDecimalValue(DictConst.MINING_RATE_AIC));
        // 会长额外奖励金额
        BigDecimal leaderRewardAIB = DecimalUtil.multiply(guildLeaderAIBPower, miningRateAIB);

        MiningDTO dto = new MiningDTO();
        dto.setPowerTotal(DecimalUtil.add(finalAIBPower, finalAICPower));
        dto.setMiningAIB(nowAIB);
        dto.setMiningAIC(nowAIC);
        dto.setMiningLeaderRewardAIB(leaderRewardAIB);
        dto.setPowerNFT(DecimalUtil.add(baseAIBPower, baseAICPower));
        dto.setPowerInvitation(DecimalUtil.add(invitationAIBPower1, invitationAICPower1, invitationAIBPower2, invitationAICPower2));
        dto.setPowerGuild(DecimalUtil.add(guildLeaderAIBPower, guildLeaderAICPower));
        dto.setPowerActivity(DecimalUtil.add(guildRankingAIBPower, guildRankingAICPower));
        return dto;
    }

    /**
     * 处理公会排名算力
     */
    private Pair<BigDecimal, BigDecimal> handleGuildRankingPower(Integer uid, BigDecimal baseAIBPower, BigDecimal baseAICPower) {
        if (!Objects.equals(Constants.SWITCH_ON, dictService.getValue(DictConst.GUILD_RANKING_SWITCH))) {
            return Pair.of(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        Guild guild = guildService.getGuildByMember(uid);
        if (guild == null) {
            return Pair.of(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        int guildRanking = guildService.getRanking(guild.getId());
        String rankingConfig = dictService.getValue(DictConst.GUILD_RANKING_REWARD_CONFIG);
        BigDecimal guildPercent = DictConfigResolver.getGuildHitPercent(rankingConfig, guildRanking);
        return Pair.of(DecimalUtil.multiply(baseAIBPower, guildPercent), DecimalUtil.multiply(baseAICPower, guildPercent));
    }

    /**
     * 处理会长算力
     */
    private Pair<BigDecimal, BigDecimal> handleGuildLeaderPower(Integer uid) {
        Guild guild = guildService.getGuildByLeader(uid);
        if (guild == null) {
            return Pair.of(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        BigDecimal guildScore = guildService.getRankScore(guild.getId());
        BigDecimal leaderPercent = dictService.getDecimalValue(DictConst.GUILD_LEADER_REWARD_PERCENT);
        return Pair.of(DecimalUtil.multiply(guildScore, leaderPercent), BigDecimal.ONE);
    }

    /**
     * 处理邀请算力
     *
     * 总算力
     * 所有用户，不分区是否签到，是否有效
     * 实时算力
     * 已签到且为有效用户，不区分级别
     * @return 返回实际邀请用户, 因为二级邀请用户仍需计算奖励
     */
    private Triple<List<Integer>, BigDecimal, BigDecimal> handleInvitationPower(List<Integer> uidList, List<Integer> validMiningUidList, BigDecimal invitationPercent, boolean isTotal) {
        BigDecimal invitationAIBPower = BigDecimal.ZERO;
        BigDecimal invitationAICPower = BigDecimal.ZERO;
        if (uidList.isEmpty() || (isTotal && validMiningUidList.isEmpty())) {
            return Triple.of(ListUtil.empty(), invitationAIBPower, invitationAICPower);
        }

        List<Integer> inviteeList = userService.selectInviteeByUidList(uidList);
        List<Integer> calcList = inviteeList;
        if (!isTotal) {
            calcList = new ArrayList<>(inviteeList);
            calcList.retainAll(validMiningUidList);
            //inviteeList = userService.selectInviteeByUidList(uidList, validMiningUidList);
        }
        if (calcList.isEmpty()) {
            return Triple.of(inviteeList, invitationAIBPower, invitationAICPower);
        }

        for (Integer tmpUid : calcList) {
            Pair<BigDecimal, BigDecimal> tmpPair = userGrantService.sumPowerByAsset(tmpUid, isTotal);
            invitationAIBPower = DecimalUtil.add(invitationAIBPower, DecimalUtil.multiply(tmpPair.getKey(), invitationPercent));
            invitationAICPower = DecimalUtil.add(invitationAICPower, DecimalUtil.multiply(tmpPair.getValue(), invitationPercent));
        }

        return Triple.of(inviteeList, invitationAIBPower, invitationAICPower);
    }

    /**
     * 根据算力变更记录差值进行合并统计出实际奖励数量
     */
    @Deprecated
    private BigDecimal calcMiningAmt(boolean allDay, List<MiningSpeedRecord> list) {
        return IntStream.range(0, list.size()).mapToObj(i -> {
            MiningSpeedRecord record = list.get(i);
            LocalDateTime endTime = getEndTime(allDay, i, list);
            long seconds = Duration.between(record.getCreateTime(), endTime).getSeconds();
            return DecimalUtil.multiply(record.getSpeed(), seconds);
        }).reduce(BigDecimal.ZERO, DecimalUtil::add);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void checkAndAddMiningSpeedRecord(Integer uid) {
        MiningRecord record = miningRecordMapper.getCurrentMiningRecord(uid);
        if (record == null) {
            return;
        }

//        BigDecimal realMiningSpeed = this.getCurrentPower(record.getUserid()).getRealMiningSpeed();
//        MiningSpeedRecord entity = miningSpeedRecordMapper.insert(record.getUserid(), record.getId(),
//                realMiningSpeed, record.getAsset());
//        AssertUtil.notNull(entity, ExceptionConstant.网络异常);
    }

    @Override
    public void miningRewardTask() {
        LambdaQueryWrapper<MiningRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningRecord::getStatus, Constants.MINING_STATUS_NOT);
        queryWrapper.le(MiningRecord::getCreateTime, LocalDateTime.now().toLocalDate());
        List<MiningRecord> miningRecords = miningRecordMapper.selectList(queryWrapper);
        log.info("miningRecords.size={}", miningRecords.size());
        if (miningRecords.isEmpty()) {
            return;
        }

        List<Integer> miningUidList = miningRecords.stream().map(MiningRecord::getUserid).collect(Collectors.toList());
        BigDecimal healthReduce = dictService.getDecimalValue(DictConst.HEALTH_REDUCE);
        // 发放挖掘奖励
        MiningService miningService = (MiningService) AopContext.currentProxy();
        miningRecords.forEach(record -> {
            taskExecutor.execute(() -> {
                miningService.handleMiningReward(record, miningUidList, healthReduce);
            });
        });
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void handleMiningReward(MiningRecord record, List<Integer> uidList, BigDecimal healthReduce) {
        Integer uid = record.getUserid();
        // 获取当前算力
        MiningDTO miningDTO = getMiningAsset(uid, uidList, false);
        log.info("mining uid {} {}", uid, miningDTO);
        // 挖掘奖励记录
        miningRewardRecordMapper.insert(record.getId(), uid, AssetTypeEnum.AIB.name(), miningDTO.getMiningAIB());
        miningRewardRecordMapper.insert(record.getId(), uid, AssetTypeEnum.AIC.name(), miningDTO.getMiningAIC());
        // 更新挖掘记录状态
        miningRecordMapper.updateStatusById(record.getId(), Constants.MINING_STATUS_OK);
        // 发放奖励
        BigDecimal a1 = DecimalUtil.sub(miningDTO.getMiningAIB(), miningDTO.getMiningLeaderRewardAIB());// 会长AIC奖励为0, aib奖励为总奖励减去
        assetService.miningRewardGrant2Frozen(uid, AssetTypeEnum.AIB, a1, AssetRecordEnum.MINING_FROZEN);
        assetService.miningRewardGrant2Frozen(uid, AssetTypeEnum.AIC, miningDTO.getMiningAIC(), AssetRecordEnum.MINING_FROZEN);
        assetService.miningRewardGrant2Frozen(uid, AssetTypeEnum.AIB, miningDTO.getMiningLeaderRewardAIB(), AssetRecordEnum.MINING_FROZEN_PRESIDENT);
        // 调整健康值
        List<UserGrant> list = userGrantService.selectList(uid);
        list.forEach(x -> {
            BigDecimal health = x.getHealth();
            if (health.compareTo(BigDecimal.ZERO) <= 0) {
                return;
            }
            userGrantService.updateHealthByCAS(x.getId(), DecimalUtil.sub(health, healthReduce), health);
        });
    }

    @Override
    public Integer countCurrentMiningRecord(Integer userid) {
        return miningRecordMapper.countToday(userid);
    }

    /**
     * 如果计算整天, 需要以第一条记录创建时间+24小时作为截止时间
     *
     * @param allDay 是否计算整天
     */
    private static LocalDateTime getEndTime(boolean allDay, int index, List<MiningSpeedRecord> miningPowerRecords) {
        if (index == miningPowerRecords.size() - 1) {
            return allDay ? miningPowerRecords.get(0).getCreateTime().plusDays(1) : LocalDateTime.now();
        }
        return miningPowerRecords.get(index + 1).getCreateTime();
    }

    @Override
    public Map<String, MiningDTO> getUserCurrentMiningAsset(Integer uid) {
        MiningDTO dto1 = getMiningAsset(uid, null, true);
        MiningDTO dto2 = miningRecordMapper.countToday(uid) == 0 ? new MiningDTO() : getMiningAsset(uid, miningRecordMapper.selectCurrentMiningUidList(), false);
        return ImmutableMap.of("total", dto1, "real", dto2);
    }

}
