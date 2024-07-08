package com.shitouren.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class MiningAssetDTO {

    private boolean mining;

    private String asset;

    private BigDecimal amt;

}
