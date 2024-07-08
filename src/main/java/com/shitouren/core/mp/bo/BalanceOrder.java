package com.shitouren.core.mp.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("balance_order")
public class BalanceOrder {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userid;
    private String orderNo;
    private BigDecimal amount;
    private Byte status;//0-已支付,待确认;1-已确认;2-取消
    private String subject;
    private String allocDetail;
    private String remark;
    private Byte source;
    private String sn;
    private LocalDateTime tradeTime;
    private LocalDateTime updatetime;
    private LocalDateTime createtime;

}
