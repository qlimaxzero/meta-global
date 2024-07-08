package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FaceStatusEnum {

    // 认证状态:0-初始化;1-认证中;2-成功;3-失败
    INIT((byte) 0, "初始化"),
    PROCESS((byte) 1, "认证中"),
    SUCC((byte) 2, "成功"),
    FAIL((byte) 3, "失败");

    private Byte status;
    private String remark;

    public static boolean isNeedAuth(Byte b) {
        if (b == null || b.compareTo(INIT.getStatus()) == 0 || b.compareTo(FAIL.getStatus()) == 0) {
            return true;
        }
        return false;
    }

    public static boolean isSucc(Byte b) {
        if (b != null && b.compareTo(SUCC.getStatus()) == 0) {
            return true;
        }
        return false;
    }

}
