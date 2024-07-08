package com.shitouren.core.bean.param.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autho： 王涛
 * @DATE： 2019/11/29 11:35
 */
@Data
public class UserLoginParam {

    @NotBlank(message = "Please enter your account number")
    private String userAccount;//登录账户

    @NotBlank(message = "Please enter password")
    @ApiModelProperty("密码")
    private String userPassword;//用户密码
}
