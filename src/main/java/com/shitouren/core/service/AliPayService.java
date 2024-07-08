package com.shitouren.core.service;

import java.math.BigDecimal;

public interface AliPayService {

    void updPay(String orderNo, BigDecimal money);

    void notify11(String orderNo, BigDecimal money);

    void updPay1(String orderNo, BigDecimal money);

    void updPay2(String out_trade_no, BigDecimal money);
}
