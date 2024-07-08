package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态相关枚举类
 * @Autho： xux
 * @DATE： 2020/8/2
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    WAIT(0, "Pending payment"),
    COMPLETE(1, "Completed"),
    CANCEL(2, "Canceled"),
    CALLBACK(-1, "Pending callback")
    ;

    private int status;
    private String desc;

    public static String getDescByType(int status) {
        for (OrderStatusEnum orderStatusEnum : OrderStatusEnum.values()) {
            if (orderStatusEnum.getStatus() == status) {
                return orderStatusEnum.getDesc();
            }
        }
        return null;
    }

}
