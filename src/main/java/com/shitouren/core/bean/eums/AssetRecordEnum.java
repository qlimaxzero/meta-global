package com.shitouren.core.bean.eums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum AssetRecordEnum {

    AIRDROP(0, "Airdrop"),
    MINING_FROZEN(1, "Freeze rewards"),
    MINING_UNFROZEN(2, "Unfreezing rewards"),
    MINING_FROZEN_PRESIDENT(3, "Guild president rewards"),
    RESTORE_HEALTH(4, "Restore Health Values"),
    GUILD(5, "Create a guild"),
    MARKET_BUY(6, "Market purchase"),
    MARKET_SELL(7, "Market sales"),
    BLINDBOX_BUY(8, "Blind box purchase"),
    EXCHANGE(9, "Exchange"),
    TRANSFER_SEND(10, "Transfer send"),
    TRANSFER_RECEIVE(11, "Transfer receive"),
    ;

    private Integer type;
    private String desc;

    public static String getDescByType(Byte type) {
        if (type == null) {
            return null;
        }
        AssetRecordEnum[] values = AssetRecordEnum.values();
        for (AssetRecordEnum e : values) {
            if (Objects.equals(e.getType(), type.intValue())) {
                return e.getDesc();
            }
        }
        return null;
    }

    public static boolean equalsAny(Integer t, AssetRecordEnum... recordEnums) {
        for (AssetRecordEnum e : recordEnums) {
            if (Objects.equals(e.getType(), t)) {
                return true;
            }
        }
        return false;
    }

}
