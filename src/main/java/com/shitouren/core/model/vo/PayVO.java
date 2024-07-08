package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PayVO {

    private String fromAddr;
    private String toAddr;
    private BigDecimal payAmt;
    private BigDecimal assetAmt;
    private String expireTime;
    private Long exchangeId;

}
