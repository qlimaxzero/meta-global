package com.shitouren.core.controller;

import com.shitouren.core.aop.Access;
import com.shitouren.core.aop.Idempotent;
import com.shitouren.core.autogenerate.bean.Users;
import com.shitouren.core.bean.param.user.UserLoginParam;
import com.shitouren.core.bean.param.user.UserRegisterParam;
import com.shitouren.core.bean.param.user.UserRestPwdParam;
import com.shitouren.core.bean.vo.user.UserLoginWx;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.service.UserService;
import com.shitouren.core.utils.StringUtil;
import com.shitouren.core.utils.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Autho： 王涛
 * @DATE： 2020/8/1 15:50
 */
@RestController
@Api(value = "用户管理", tags = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 商城账号密码登录
     *
     * @param param
     * @return
     */
    @PostMapping("/user/login")
    @Access(value = false)
    @ApiOperation(value = "用户-密码登录")
    @Idempotent
    public Map<String, Object> mallLogin(@Valid UserLoginParam param) {
        Users user = userService.userLogin(param);
        String token = TokenUtil.getToken(new UserLoginWx(user));
        if (!StringUtil.isValidStr(token)) {
            throw new CloudException(ExceptionConstant.获取token失败.getCode());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("uid", user.getUserId());
        return map;
    }

//    @PostMapping("/user/codelogin")
//    @Access(value = false)
//    @ApiOperation(value = "用户-验证码登录")
//    public Map<String, Object> codelogin(@Valid NewUserLoginParam param) {
//        Users user = userService.codeuserLogin(param);
//        String token = TokenUtil.getToken(new UserLoginWx(user));
//        if (!StringUtil.isValidStr(token)) {
//            throw new CloudException(ExceptionConstant.获取token失败.getCode());
//        }
//        Map<String, Object> map = new HashMap<>();
//        map.put("token", token);
//        return map;
//    }

    /**
     * 用户注册
     *
     * @param param
     */
    @PostMapping("/user/reg")
    @Access(value = false)
    @ApiOperation(value = "用户-注册")
    @Idempotent
    public void userRegister(@Valid UserRegisterParam param) {
        userService.userRegister(param);
    }

    /**
     * 忘记密码
     *
     * @param param
     */
    @PostMapping("/user/reset/pwd")
    @ApiOperation(value = "用户-忘记密码")
    @Access(value = false)
    @Idempotent
    public void userRestPwd(@Valid UserRestPwdParam param) {
        userService.userRestPwd(param);
    }


}
