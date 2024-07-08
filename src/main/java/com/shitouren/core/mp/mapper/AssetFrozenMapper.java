package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.common.Constants;
import com.shitouren.core.mp.bo.AssetFrozen;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AssetFrozenMapper extends BaseMapper<AssetFrozen> {

    default AssetFrozen save(Integer uid, AssetTypeEnum asset, BigDecimal frozenAmt, Byte status, long frozenDuration) {
        AssetFrozen entity = new AssetFrozen();
        entity.setUserid(uid);
        entity.setAsset(asset.name());
        entity.setFrozenAmt(frozenAmt);
        entity.setStatus(status);
        LocalDateTime now = LocalDateTime.now();
        entity.setUnfrozenTime(now.plusSeconds(frozenDuration));
        entity.setUpdateTime(now);
        entity.setCreateTime(now);

        return insert(entity) == 1 ? entity : null;
    }

    default boolean unfrozenById(AssetFrozen entity) {
        entity.setStatus(Constants.ASSET_STATUS_UNFROZEN);
        entity.setUpdateTime(LocalDateTime.now());
        return this.updateById(entity) == 1;
    }

}
