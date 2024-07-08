package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.common.Constants;
import com.shitouren.core.mp.bo.MiningRecord;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface MiningRecordMapper extends BaseMapper<MiningRecord> {

    default MiningRecord getCurrentMiningRecord(Integer uid) {
        LambdaQueryWrapper<MiningRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningRecord::getUserid, uid);
        queryWrapper.eq(MiningRecord::getStatus, Constants.MINING_STATUS_NOT);
        return this.selectOne(queryWrapper);
    }

    default List<MiningRecord> selectCurrentMiningList() {
        LambdaQueryWrapper<MiningRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningRecord::getStatus, Constants.MINING_STATUS_NOT);
        return this.selectList(queryWrapper);
    }

    default List<Integer> selectCurrentMiningUidList() {
        LambdaQueryWrapper<MiningRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningRecord::getStatus, Constants.MINING_STATUS_NOT);
        queryWrapper.select(MiningRecord::getUserid);
        List<Object> objects = this.selectObjs(queryWrapper);
        return objects.stream().map(o -> (Integer) o).collect(Collectors.toList());
    }

    default Integer count(Integer uid) {
        LambdaQueryWrapper<MiningRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningRecord::getUserid, uid);
        queryWrapper.eq(MiningRecord::getStatus, Constants.MINING_STATUS_NOT);
        return this.selectCount(queryWrapper);
    }

    default Integer countToday(Integer uid) {
        LambdaQueryWrapper<MiningRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MiningRecord::getUserid, uid);
        queryWrapper.eq(MiningRecord::getStatus, Constants.MINING_STATUS_NOT);
        queryWrapper.gt(MiningRecord::getCreateTime, LocalDateTime.now().toLocalDate());
        return this.selectCount(queryWrapper);
    }

    default MiningRecord insert(Integer uid, String miningAsset, Byte status) {
        LocalDateTime now = LocalDateTime.now();
        MiningRecord entity = MiningRecord.builder()
                .userid(uid)
                .asset(miningAsset)
                .status(status)
                .updateTime(now)
                .createTime(now)
                .build();
        return insert(entity) == 1 ? entity : null;
    }

    default int updateStatusById(Long id, Byte status) {
        return this.updateById(MiningRecord.builder()
                .id(id)
                .status(status)
                .updateTime(LocalDateTime.now())
                .build());
    }

}
