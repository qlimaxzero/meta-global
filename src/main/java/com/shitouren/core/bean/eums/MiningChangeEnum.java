package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MiningChangeEnum {

    INVITE("邀请用户"),
    NFT("NFT交易"),
    HEALTH("健康值变更"),
    ;

    private String desc;

}
