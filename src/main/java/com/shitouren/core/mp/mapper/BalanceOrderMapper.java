package com.shitouren.core.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.shitouren.core.mp.bo.BalanceOrder;

public interface BalanceOrderMapper extends BaseMapper<BalanceOrder> {

    default BalanceOrder getByOrderNo(String orderNo, Byte source) {
        return this.selectOne(new LambdaQueryWrapper<BalanceOrder>()
                .eq(BalanceOrder::getOrderNo, orderNo)
                .eq(BalanceOrder::getSource, source));
    }

    default boolean checkExist(String outOrderNo, Byte source) {
        return this.selectCount(new LambdaQueryWrapper<BalanceOrder>()
                .eq(BalanceOrder::getOrderNo, outOrderNo)
                .eq(BalanceOrder::getSource, source)) > 0;
    }

    default BalanceOrder getBySn(String sn) {
        return this.selectOne(new LambdaQueryWrapper<BalanceOrder>()
                .eq(BalanceOrder::getSn, sn));
    };

}
