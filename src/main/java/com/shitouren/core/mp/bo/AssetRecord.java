package com.shitouren.core.mp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AssetRecord {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer userid;

    /** 资产名 */
    private String asset;

    /** 记录类型 */
    private Byte type;

    /** 金额 */
    private BigDecimal amt;

    /** 关联id */
    private String relatedIds;

    /** 当前金额 */
    private BigDecimal currentAmt;

    private LocalDateTime createTime;

}
