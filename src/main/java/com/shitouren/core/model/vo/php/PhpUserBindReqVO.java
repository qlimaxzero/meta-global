package com.shitouren.core.model.vo.php;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "phpUserBindReqVO")
public class PhpUserBindReqVO {

    @ApiModelProperty(value = "上次绑定账号")
    private String oldUsername;

    @NotBlank(message = "登录账号不能为空")
    @ApiModelProperty(value = "登录账号:手机号或邮箱")
    private String username;

    @NotBlank(message = "场景id不能为空")
    @ApiModelProperty(value = "场景id")
    private String mapId;

    @NotNull(message = "店铺id")
    @ApiModelProperty(value = "店铺id")
    private Integer shopId;

}
