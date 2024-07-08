package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class BlindBoxVO {

    private Integer id;
    private String name;
    private String imgUrl;
    private BigDecimal price;
    private Integer limit;
    private String desc;
    private String payType;

}
