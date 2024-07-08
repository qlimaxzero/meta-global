package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UsageEnum {

    NOT_USED(0, "未使用"),
    USED(1, "已使用"),
    ;
    private final Integer k;
    private final String v;

    public static boolean isUsed(int v) {
        return USED.k.equals(v);
    }

    public static boolean isUsed(Byte v) {
        if (v == null) return false;
        return USED.k.equals(v.intValue());
    }

}
