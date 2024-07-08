package com.shitouren.core.model.vo.pi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "LoginVO", description = "前端调SDK获取的用户信息")
public class LoginVO implements Serializable {

    @ApiModelProperty(value = "token", dataType = "String")
    @NotBlank
    private String accessToken;

    @ApiModelProperty(value = "用户ID", dataType = "String")
    @NotBlank
    private String uid;

    @ApiModelProperty(value = "用户名", dataType = "String")
    private String userName;

}
