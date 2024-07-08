package com.shitouren.core.model.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @description: 银行卡提现
 * @author: Donugh
 * @create: 2023-05-28 16:21
 **/

@Data
public class BankWithdrawVO {
    @NotBlank(message = "银行卡号不能为空")
    private String bankCardNo;

    @NotNull(message = "提现金额不能为空")
    private BigDecimal money;
}