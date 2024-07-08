package com.shitouren.core.service;

import com.shitouren.core.model.vo.php.*;

import java.util.List;
import java.util.Map;

public interface PhpService {

    /**
     * 商城增减积分
     */
    void shopPoints(PhpShopPointsReqVO reqVO);

    /**
     * 校验用户是否存在并返回用户积分信息
     */
    Map<String, Object> userPoints(String username);

    /**
     * php商城发起余额支付订单
     */
    String balanceOrderSubmit(PhpBalanceOrderSubmitReqVO reqVO);

    /**
     * php商城发起余额支付订单确认
     */
    void balanceOrderConfirm(PhpBalanceOrderConfirmReqVO reqVO);

}
