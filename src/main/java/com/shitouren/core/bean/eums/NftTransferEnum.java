package com.shitouren.core.bean.eums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum NftTransferEnum {

    AIRDROP(0, "Airdrop"),
    GIFTS(1, "Gifts"),
    BLINDBOX_BUY(2, "Blind box purchase"),
    MARKET_BUY(3, "Market purchase"),
    MARKET_SELL(4, "Market sales"),
    ;

    private Integer type;
    private String desc;

    public static String getDescByType(Integer type) {
        NftTransferEnum[] values = NftTransferEnum.values();
        for (NftTransferEnum e : values) {
            if (Objects.equals(e.getType(), type)) {
                return e.getDesc();
            }
        }
        return null;
    }

    public static boolean equalsAny(Integer t, NftTransferEnum... transferEnums) {
        for (NftTransferEnum e : transferEnums) {
            if (Objects.equals(e.getType(), t)) {
                return true;
            }
        }
        return false;
    }

}
