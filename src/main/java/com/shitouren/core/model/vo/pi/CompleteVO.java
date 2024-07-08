package com.shitouren.core.model.vo.pi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "CompleteVO", description = "支付完成对象")
public class CompleteVO implements Serializable {

    @ApiModelProperty(value = "PI支付ID", dataType = "String")
    @NotBlank
    private String paymentId;

    @ApiModelProperty(value = "txid", dataType = "String")
    @NotBlank
    private String txid;

}
