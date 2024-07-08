package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum AssetTypeEnum {

    AIB(1),
    AIC(2),
    ;

    private Integer type;

    public static boolean isAsset(Integer t) {
        AssetTypeEnum[] values = AssetTypeEnum.values();
        for (AssetTypeEnum value : values) {
            if (value.getType().equals(t)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAsset(String name) {
        AssetTypeEnum[] values = AssetTypeEnum.values();
        for (AssetTypeEnum value : values) {
            if (Objects.equals(name, value.name())) {
                return true;
            }
        }
        return false;
    }

    public static AssetTypeEnum getAsset(Integer t) {
        AssetTypeEnum[] values = AssetTypeEnum.values();
        for (AssetTypeEnum value : values) {
            if (value.getType().equals(t)) {
                return value;
            }
        }
        //throw new CloudException(ExceptionConstant.参数异常);
        return null;
    }

}
