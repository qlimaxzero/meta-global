package com.shitouren.core.mp.bo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class MiningRewardRecord {

    /** 主键id */
    private Long id;

    /** 挖掘记录id */
    private Long miningId;

    /** 用户id */
    private Integer userid;

    /** 资产名 */
    private String asset;

    /** 奖励金额 */
    private BigDecimal amt;

    private LocalDateTime createTime;

}
