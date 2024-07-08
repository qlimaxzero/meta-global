package com.shitouren.core.service;



import com.shitouren.core.model.dto.MiningDTO;
import com.shitouren.core.mp.bo.MiningRecord;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MiningService {

    void startMining(Integer uid);

    /**
     * 获取当前用户算力信息及当日收益
     */
    MiningDTO getMiningAsset(Integer uid, List<Integer> miningUidList, boolean isSign);

    void checkAndAddMiningSpeedRecord(Integer uid);

    /**
     * 挖掘奖励任务
     */
    void miningRewardTask();

    void handleMiningReward(MiningRecord record, List<Integer> miningUidList, BigDecimal healthReduce);

    Integer countCurrentMiningRecord(Integer userid);

    Map<String, MiningDTO> getUserCurrentMiningAsset(Integer uid);

}
