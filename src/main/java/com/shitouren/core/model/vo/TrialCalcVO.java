package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class TrialCalcVO {

    private BigDecimal sellPrice;
    private BigDecimal realPrice;
    private BigDecimal serviceFee;
    private BigDecimal serviceFeeRate;

}
