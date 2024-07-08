package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.bean.eums.AssetTypeEnum;
import com.shitouren.core.bean.eums.OrderStatusEnum;
import com.shitouren.core.mp.bo.ExchangeRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ExchangeMapper extends BaseMapper<ExchangeRecord> {

    default ExchangeRecord getExchange(Integer uid, OrderStatusEnum statusEnum) {
        LambdaQueryWrapper<ExchangeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExchangeRecord::getUserid, uid);
        queryWrapper.eq(ExchangeRecord::getStatus, statusEnum.getStatus());
        List<ExchangeRecord> list = selectList(queryWrapper);
        return list.isEmpty() ? null : list.get(0);
    }

    default ExchangeRecord getLastExchange(Integer uid) {
        LambdaQueryWrapper<ExchangeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExchangeRecord::getUserid, uid);
        queryWrapper.orderByDesc(ExchangeRecord::getId);
        queryWrapper.last("limit 1");
        return selectOne(queryWrapper);
    }

    default ExchangeRecord createPayingRecord(Integer uid, Integer saleId, Integer payType, BigDecimal payAmt, AssetTypeEnum asset, BigDecimal assetAmt) {
        LocalDateTime now = LocalDateTime.now();
        ExchangeRecord record = ExchangeRecord.builder()
                .userid(uid)
                .payType(payType).payAmt(payAmt)
                .assetSaleId(saleId).assetName(asset.name()).assetAmt(assetAmt)
                .status(OrderStatusEnum.WAIT.getStatus())
                .updateTime(now)
                .createTime(now)
                .build();
        int i = insert(record);
        return i == 1 ? record : null;
    }

    default boolean updateById(Long id, String receiveAddr, String txId) {
        LambdaUpdateWrapper<ExchangeRecord> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ExchangeRecord::getId, id);
        updateWrapper.set(ExchangeRecord::getUpdateTime, LocalDateTime.now());
//        updateWrapper.set(fromAddr != null, ExchangeRecord::getFromAddr, fromAddr);
        updateWrapper.set(receiveAddr != null, ExchangeRecord::getToAddr, receiveAddr);
        updateWrapper.set(txId != null, ExchangeRecord::getOutOrderNo, txId);
        return update(null, updateWrapper) == 1;
    }

    default List<ExchangeRecord> selectRecord(OrderStatusEnum statusEnum) {
        LambdaQueryWrapper<ExchangeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExchangeRecord::getStatus, statusEnum.getStatus());
        return selectList(queryWrapper);
    }

    default boolean selectExistsRepeatToAddr(String toAddr, BigDecimal payAmt, OrderStatusEnum statusEnum) {
        LambdaQueryWrapper<ExchangeRecord> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ExchangeRecord::getToAddr, toAddr);
        queryWrapper.eq(ExchangeRecord::getPayAmt, payAmt);
        queryWrapper.eq(ExchangeRecord::getStatus, statusEnum.getStatus());
        Integer i = selectCount(queryWrapper);
        return i > 0;
    }

}
