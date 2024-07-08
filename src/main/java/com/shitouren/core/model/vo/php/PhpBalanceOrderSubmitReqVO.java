package com.shitouren.core.model.vo.php;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@ApiModel(value = "phpBalanceOrderSubmitReqVO")
public class PhpBalanceOrderSubmitReqVO {

    @NotBlank(message = "用户不能为空")
    @ApiModelProperty(value = "用户:手机号或邮箱")
    private String username;

    @NotBlank(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @Positive(message = "金额必须大于0")
    @NotNull(message = "金额不能为空")
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @NotBlank(message = "订单标题不能为空")
    @ApiModelProperty(value = "订单标题")
    private String subject;

    @NotNull(message = "分账明细不能为空")
    @ApiModelProperty(value = "分账明细(用户名1:金额1,用户名2:金额2)")
    private String allocDetail;

    @ApiModelProperty(value = "备注")
    private String remark;

    @NotBlank(message = "订单时间不能为空")
    @ApiModelProperty(value = "订单发起时间")
    private String tradeTime;

    @ApiModelProperty(value = "来源")
    private Byte source = 1;

}
