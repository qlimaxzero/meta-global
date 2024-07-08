package com.shitouren.core.mp.bo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ExchangeRecord {

    /** 主键id */
    private Long id;

    /** 用户id */
    private Integer userid;

    /** 支付类型 */
    private Integer payType;

    /** 支付金额 */
    private BigDecimal payAmt;

    /** 售卖资产id */
    private Integer assetSaleId;

    /** 资产名 */
    private String assetName;

    /** 资产数量 */
    private BigDecimal assetAmt;

    /** 记录类型 */
    private Integer status;

    /** 外部单号 */
    private String outOrderNo;

    private String fromAddr;

    private String toAddr;

    private LocalDateTime successTime;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
