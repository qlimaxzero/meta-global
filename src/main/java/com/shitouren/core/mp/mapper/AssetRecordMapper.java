package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.bean.eums.AssetRecordEnum;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.mp.bo.AssetRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AssetRecordMapper extends BaseMapper<AssetRecord> {

    default int save(Integer uid, AssetTypeEnum asset, AssetRecordEnum type, BigDecimal amt, BigDecimal totalAmt, String relatedIds) {
        AssetRecord entity = new AssetRecord();
        entity.setUserid(uid);
        entity.setAsset(asset.name());
        entity.setType(type.getType().byteValue());
        entity.setAmt(amt);
        entity.setRelatedIds(relatedIds);
        entity.setCurrentAmt(totalAmt);
        entity.setCreateTime(LocalDateTime.now());
        return this.insert(entity);
    }

    default AssetRecord getLastAssetRecord(Integer uid) {
        LambdaQueryWrapper<AssetRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetRecord::getUserid, uid);
        queryWrapper.orderByDesc(AssetRecord::getId);
        queryWrapper.last("limit 1");
        return selectOne(queryWrapper);
    }

}
