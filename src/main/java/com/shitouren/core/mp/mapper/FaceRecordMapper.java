package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.FaceRecord;

public interface FaceRecordMapper extends BaseMapper<FaceRecord> {

    default FaceRecord selectByUserId(Integer userId) {
        LambdaQueryWrapper<FaceRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FaceRecord::getUserid, userId);
        return this.selectOne(queryWrapper);
    }

    default FaceRecord selectBySn(String sn) {
        LambdaQueryWrapper<FaceRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FaceRecord::getSn, sn);
        return this.selectOne(queryWrapper);
    }

    default int saveOrUpdate(FaceRecord record) {
        if (record.getId() == null) {
            return this.insert(record);
        } else {
            return this.updateById(record);
        }
    }

}
