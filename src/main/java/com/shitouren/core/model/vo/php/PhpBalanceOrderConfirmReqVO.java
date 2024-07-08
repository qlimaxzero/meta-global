package com.shitouren.core.model.vo.php;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import com.shitouren.core.enums.BalanceOrderStatusEnum;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "phpBalanceOrderConfirmReqVO")
public class PhpBalanceOrderConfirmReqVO {

    //@NotBlank(message = "流水号不能为空")
    @ApiModelProperty(value = "流水号")
    private String sn;

    //@NotBlank(message = "订单号不能为空")
    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @NotNull(message = "状态不能为空")
    @ApiModelProperty(value = "确认状态")
    private BalanceOrderStatusEnum status;

    @ApiModelProperty(value = "来源")
    private Byte source = 1;

}
