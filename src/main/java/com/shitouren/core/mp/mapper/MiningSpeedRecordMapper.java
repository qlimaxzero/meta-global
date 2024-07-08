package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.MiningSpeedRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface MiningSpeedRecordMapper extends BaseMapper<MiningSpeedRecord> {

    default List<MiningSpeedRecord> selectListByMining(Long miningId, Integer uid, String asset) {
        LambdaQueryWrapper<MiningSpeedRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningSpeedRecord::getMiningId, miningId);
        queryWrapper.eq(MiningSpeedRecord::getUserid, uid);
        queryWrapper.eq(MiningSpeedRecord::getAsset, asset);
        return this.selectList(queryWrapper);
    }

    default MiningSpeedRecord insert(Integer uid, Long miningId, BigDecimal currentSpeed, String miningAsset) {
        MiningSpeedRecord entity = MiningSpeedRecord.builder()
                .userid(uid)
                .miningId(miningId)
                .speed(currentSpeed)
                .asset(miningAsset)
                .createTime(LocalDateTime.now())
                .build();
        return insert(entity) == 1 ? entity : null;
    }

    default List<MiningSpeedRecord> selectList(Object[] array) {
        LambdaQueryWrapper<MiningSpeedRecord> powerQueryWrapper = new LambdaQueryWrapper<>();
        powerQueryWrapper.in(MiningSpeedRecord::getMiningId, array);
        return this.selectList(powerQueryWrapper);
    }

}
