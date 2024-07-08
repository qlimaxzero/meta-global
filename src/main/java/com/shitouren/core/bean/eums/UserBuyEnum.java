package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserBuyEnum {

    MARKET("Market transactions"),
    BLIND_BOX("Blind box purchase"),
    ;

    private String desc;

}
