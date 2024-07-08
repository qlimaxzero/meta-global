package com.shitouren.core.mp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AssetUser {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer userid;

    /** 资产名 */
    private String asset;

    /** 总金额 */
    private BigDecimal totalAmt;

    /** 可用金额 */
    private BigDecimal usableAmt;

    /** 冻结金额 */
    private BigDecimal frozenAmt;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
