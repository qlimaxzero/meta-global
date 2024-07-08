package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class RecordVO {

    private Long id;
    private LocalDateTime createTime;
    private LocalDateTime expireTime;
    private Integer type;
    private String typeName;
    private String desc;

    private String assetName;
    private BigDecimal assetAmt;
    private Integer payType;
    private BigDecimal payAmt;
    private String ratio;
    private String toAddr;

}
