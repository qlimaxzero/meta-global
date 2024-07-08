package com.shitouren.core.model.vo.pi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@ApiModel(value = "PaymentVO", description = "支付对象")
public class PaymentVO implements Serializable {

    @ApiModelProperty(value = "我方订单号", dataType = "String")
    private Integer orderId;

    @ApiModelProperty(value = "PI支付ID", dataType = "String")
    @NotBlank
    private String paymentId;

}
