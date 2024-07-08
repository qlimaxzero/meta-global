package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BalanceTypeEnum {

    COMMISSION((byte) 0, "佣金"),//extra=myOrderId
    RECHARGE((byte) 1, "充值"),//extra=rechargeRecordId
    CASHOUT((byte) 2, "提现"),
    SALE((byte) 3, "出售"),//内部二级市场 extra=卖出的grantId
    BUY((byte) 4, "模块"),//extra=myOrderId
    TRADE((byte) 5, "交易"),//内部二级市场购买 extra=买入后的grantId
    LOTTERY((byte) 6, "抽签"),
    SHOP((byte) 7, "商品"),//extra=余额支付订单id(balanceOrderId)
    ;

    private Byte type;
    private String desc;

    public static String getDescByType(Byte type) {
        for (BalanceTypeEnum balanceTypeEnum : BalanceTypeEnum.values()) {
            if (balanceTypeEnum.getType().equals(type)) {
                return balanceTypeEnum.getDesc();
            }
        }
        return null;
    }

}
