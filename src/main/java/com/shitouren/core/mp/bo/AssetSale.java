package com.shitouren.core.mp.bo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AssetSale {

    /** 主键id */
    private Integer id;

    /** 资产名 */
    private String asset;

    /** 开售金额 */
    private BigDecimal amt;

    /** 已售 */
    private BigDecimal sold;

    /** 限额 */
    private BigDecimal quota;

    /** 开售时间 */
    private LocalDateTime startTime;

    /** 截止时间 */
    private LocalDateTime endTime;

    /** 支付方式:比例  */
    private String payRatio;

    /** 顺序 */
    private Integer sort;

    /** 0-下架;1-上架 */
    private Byte status;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
