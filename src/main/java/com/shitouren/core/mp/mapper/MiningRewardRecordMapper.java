package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.MiningRewardRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface MiningRewardRecordMapper extends BaseMapper<MiningRewardRecord> {

    default MiningRewardRecord insert(Long miningId, Integer uid, String asset, BigDecimal miningAmt) {
        MiningRewardRecord entity = MiningRewardRecord.builder()
                .miningId(miningId)
                .userid(uid)
                .asset(asset)
                .amt(miningAmt)
                .createTime(LocalDateTime.now())
                .build();
        return insert(entity) == 1 ? entity : null;
    }

}
