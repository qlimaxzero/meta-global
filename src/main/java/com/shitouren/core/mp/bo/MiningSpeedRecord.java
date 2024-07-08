package com.shitouren.core.mp.bo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MiningSpeedRecord {

    /** 主键id */
    private Long id;

    /** 挖掘记录id */
    private Long miningId;

    /** 用户id */
    private Integer userid;

    /** 资产名 */
    private String asset;

    /** 挖掘速度/s=算力*速率 */
    private BigDecimal speed;

    private LocalDateTime createTime;

}
