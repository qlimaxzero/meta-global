package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class SellNFTVO {

    private Integer id;
    private String name;
    private String no;
    private String imgUrl;
    private BigDecimal price;

}
