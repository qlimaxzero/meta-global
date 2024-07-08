package com.shitouren.core.service;

import com.shitouren.core.model.vo.pi.CompleteVO;
import com.shitouren.core.model.vo.pi.IncompleteVO;
import com.shitouren.core.model.vo.pi.LoginVO;
import com.shitouren.core.model.vo.pi.PaymentVO;

import java.math.BigDecimal;

public interface PiService {

    void auth(LoginVO vo);

    /**
     * 先处理未完成的订单
     */
    void incomplete(IncompleteVO vo);

    /**
     * 确认支付订单信息
     * @param orderId 订单id
     * @param paymentId 外部支付id
     * @param expPayAmt 预期付款金额
     */
    void approve(Integer orderId, String paymentId, BigDecimal expPayAmt);

    void complete(CompleteVO vo);

    void cancel(PaymentVO vo);

}
