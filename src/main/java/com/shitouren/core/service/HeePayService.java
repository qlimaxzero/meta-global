package com.shitouren.core.service;

import com.shitouren.core.model.dto.VirtualCoinDTO;

import java.math.BigDecimal;

public interface HeePayService {

    void updPay(String orderNo, BigDecimal money, VirtualCoinDTO coinDTO);

    void notify11(String orderNo, BigDecimal money);

    void notifyRecharge(String orderNo, BigDecimal money);

    void updPay2(String out_trade_no, BigDecimal money);
}
