package com.shitouren.core.mp.bo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@TableName("balance_order_alloc")
public class BalanceOrderAlloc {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer balanceId;
    private Integer sellerId;
    private BigDecimal amount;
    private LocalDateTime createtime;

}
