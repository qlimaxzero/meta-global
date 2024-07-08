package com.shitouren.core.bean.param.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Autho： 王涛
 * @DATE： 2020/8/2 13:31
 */
@Data
@ApiModel
public class UserRestPwdParam {

    @NotBlank(message = "Please enter your email address")
    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("验证码")
    @NotBlank(message = "Please enter the verification code")
    private String code;

    @ApiModelProperty("密码")
    @NotBlank(message = "Please enter password")
    private String password;

    @ApiModelProperty("确认密码")
    @NotBlank(message = "Please confirm password")
    private String password2;

    @ApiModelProperty(value = "0.手机号1.邮箱")
    private int state;

}
