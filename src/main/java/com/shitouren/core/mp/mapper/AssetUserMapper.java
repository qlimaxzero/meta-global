package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.mp.bo.AssetUser;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AssetUserMapper extends BaseMapper<AssetUser> {

    default AssetUser initAsset(Integer uid, AssetTypeEnum asset) {
        AssetUser entity = new AssetUser();
        entity.setUserid(uid);
        entity.setAsset(asset.name());
        entity.setTotalAmt(BigDecimal.ZERO);
        entity.setFrozenAmt(BigDecimal.ZERO);
        entity.setUsableAmt(BigDecimal.ZERO);
        LocalDateTime now = LocalDateTime.now();
        entity.setUpdateTime(now);
        entity.setCreateTime(now);
        return insert(entity) == 1 ? entity : null;
    }

    default boolean updateAmtByCAS(AssetUser entity, BigDecimal oldUsableAmt, BigDecimal oldFrozenAmt) {
        LambdaUpdateWrapper<AssetUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AssetUser::getId, entity.getId());
        updateWrapper.eq(oldUsableAmt != null, AssetUser::getUsableAmt, oldUsableAmt);
        updateWrapper.eq(oldFrozenAmt != null, AssetUser::getFrozenAmt, oldFrozenAmt);
        entity.setUpdateTime(LocalDateTime.now());
        return this.update(entity, updateWrapper) == 1;
    }

    default AssetUser getAssetUser(Integer uid, AssetTypeEnum asset) {
        LambdaQueryWrapper<AssetUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetUser::getUserid, uid);
        queryWrapper.eq(AssetUser::getAsset, asset.name());
        return this.selectOne(queryWrapper);
    }

}
