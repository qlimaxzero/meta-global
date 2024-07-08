package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class GuildVO {
    private Integer id;
    private int ranking;
    private String name;
    private String code;
    private BigDecimal power;
}
