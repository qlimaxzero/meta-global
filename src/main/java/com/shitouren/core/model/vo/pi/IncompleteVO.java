package com.shitouren.core.model.vo.pi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "IncompleteDto", description = "失败订单对象")
public class IncompleteVO implements Serializable {

    @ApiModelProperty(value = "paymentId", dataType = "String")
    @NotBlank(message = "Pi支付id不能为空")
    private String paymentId;

    @ApiModelProperty(value = "txid", dataType = "String")
    @NotBlank(message = "区块链交易id不能为空")
    private String txid;

    @ApiModelProperty(value = "link", dataType = "String")
    private String link;

}
