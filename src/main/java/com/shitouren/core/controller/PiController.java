package com.shitouren.core.controller;

import com.shitouren.core.aop.Access;
import com.shitouren.core.model.vo.pi.*;
import com.shitouren.core.service.PiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

//@Api(tags = {"PI后端API部分"})
@RestController
@RequestMapping("/pi")
@Deprecated
public class PiController {

    @Resource
    private PiService piService;

    /**
     * 由前端发起的用户登录鉴权, 相当于三方登录
     */
    //@ApiOperation(value = "PI用户登录验权,获得token(header带上token)")
    @PostMapping("/access/auth")
    @Access
    public void auth(LoginVO loginVO) {
        piService.auth(loginVO);
    }

    //@ApiOperation(value = "处理未完成的订单")
    @PostMapping("/payments/incomplete")
    @Access
    public void incomplete(IncompleteVO vo) {
        piService.incomplete(vo);
    }

    //@ApiOperation(value = "前端请求支付授权,在本地订单创建后调")
    @PostMapping("/payments/approve")
    @Access
    @Deprecated // 原有payorder代替
    public void approve(PaymentVO vo) {
        //piService.approve(vo);
    }

    //@ApiOperation(value = "前端支付完成")
    @PostMapping("/payments/complete")
    @Access
    public void complete(CompleteVO vo) {
        piService.complete(vo);
    }

    //@ApiOperation(value = "取消支付,订单关闭")
    @PostMapping("/payments/cancel")
    @Access
    @Deprecated
    public void cancelledPayment(PaymentVO vo) {
        piService.cancel(vo);
    }

}
