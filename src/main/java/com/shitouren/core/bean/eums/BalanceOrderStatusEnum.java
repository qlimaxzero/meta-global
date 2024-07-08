package com.shitouren.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BalanceOrderStatusEnum {

    // 订单状态:0-已支付,待确认;1-已确认;2-取消
    PAY((byte) 0, "已支付,待确认", "余额支付"),
    CONFIRM((byte) 1, "已确认", "余额入账"),
    CANCEL((byte) 2, "取消", "余额退款");

    private Byte status;
    private String remark;
    private String balanceDesc;

    public static boolean isPay(Byte status) {
        return status.equals(PAY.getStatus());
    }

    public static boolean isFinalState(Byte status) {
        return !status.equals(PAY.getStatus());
    }

    public static boolean isFinalState(BalanceOrderStatusEnum status) {
        return !status.equals(PAY);
    }

}
