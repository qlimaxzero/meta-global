package com.shitouren.core.model.vo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class RecordNFTVO {

    private Long id;
    private LocalDateTime createTime;
    private Integer type;
    private String typeName;
    private String title;
    private String desc;

}
