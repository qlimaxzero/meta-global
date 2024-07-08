package com.shitouren.core.mp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AssetFrozen {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer userid;

    /** 资产名 */
    private String asset;

    /** 冻结金额 */
    private BigDecimal frozenAmt;

    /** 预期解冻时间 */
    private LocalDateTime unfrozenTime;

    /** 状态: 0-已冻结;1-已解冻 */
    private Byte status;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
