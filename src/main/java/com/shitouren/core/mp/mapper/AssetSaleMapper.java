package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.common.Constants;
import com.shitouren.core.mp.bo.AssetSale;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AssetSaleMapper extends BaseMapper<AssetSale> {

    default AssetSale getCurrentAssetSale(AssetTypeEnum asset) {
        LambdaQueryWrapper<AssetSale> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AssetSale::getAsset, asset);
        queryWrapper.eq(AssetSale::getStatus, Constants.ASSET_SALE_UP);
        queryWrapper.lt(AssetSale::getStartTime, LocalDateTime.now());
        queryWrapper.gt(AssetSale::getEndTime, LocalDateTime.now());
        queryWrapper.apply("amt - sold > 0");
        queryWrapper.orderByDesc(AssetSale::getSort);
        queryWrapper.last("limit 1");
        return selectOne(queryWrapper);
    }

    default boolean updateSoldByCAS(Integer id, BigDecimal newSold, BigDecimal oldSold) {
        LambdaUpdateWrapper<AssetSale> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(AssetSale::getId, id);
        updateWrapper.eq(AssetSale::getSold, oldSold);
        updateWrapper.set(AssetSale::getSold, newSold);
        return update(null, updateWrapper) == 1;
    }

}
