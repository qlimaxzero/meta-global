package com.shitouren.core.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class NFTHealthDTO {

    private boolean stop;
    private BigDecimal useHealth;
    private int remainingWordCount;

}
