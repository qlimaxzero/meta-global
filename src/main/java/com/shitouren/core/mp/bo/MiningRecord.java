package com.shitouren.core.mp.bo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MiningRecord {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer userid;

    /** 资产名 */
    private String asset;

    /** 发放状态:0-未发放;1-已发放 */
    private Byte status;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
