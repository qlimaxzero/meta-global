package com.shitouren.core.controller;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.shitouren.core.aop.Access;
import com.shitouren.core.common.Constants;
import com.shitouren.core.aop.Idempotent;
import com.shitouren.core.aop.LimitType;
import com.shitouren.core.aop.RateLimiter;
import com.shitouren.core.autogenerate.dao.UsersDao;
import com.shitouren.core.bean.param.system.VerificationCodeParam;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.mail.MailUtils;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.service.HomeService;
import com.shitouren.core.service.MineService;
import com.shitouren.core.utils.AssertUtil;
import com.shitouren.core.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "系统管理", tags = "系统管理")
@Slf4j
public class SystemController {
//    private static Map<String, String> tempMap = new HashMap<>();
//
//    static {
//        tempMap.put("reg_code_", "【dopai元宇宙】您的验证码为%s，在5分钟内有效。");
//        tempMap.put("forget_pwd_code_", "【dopai元宇宙】您的验证码为%s，在5分钟内有效。");
//        tempMap.put("update_trade_pwd_", "【dopai元宇宙】您的验证码为%s，在5分钟内有效。");
//        tempMap.put("check_code_", "【dopai元宇宙】您的验证码为%s，在5分钟内有效。");
//        tempMap.put("alipay_code_", "【dopai元宇宙】您的验证码为%s，在5分钟内有效。");
//    }

    @Autowired
    private CloudRedisTemplate cloudRedisTemplate;
    @Autowired(required = false)
    UsersDao usersDao;
    @Autowired
    HomeService homeService;
    @Autowired
    MineService mineService;
    @Resource
    MailUtils mailUtils;

    @SneakyThrows
    @PostMapping(value = "/system/get/verification/code")
    @ApiOperation(value = "获取手机验证码")
    @Access(value = false)
    @RateLimiter(count = 5, limitType = LimitType.IP)
    public String getPhoneCode(@Validated VerificationCodeParam param) {
        String codeLimitKey = Constants.CODE_LIMIT_KEY + param.getPhone();
        long num = cloudRedisTemplate.incr(codeLimitKey, 1);
        long sec = DateUtil.between(new Date(), DateUtil.endOfDay(new Date()), DateUnit.SECOND);
        cloudRedisTemplate.expire(codeLimitKey, sec);
        AssertUtil.isFalse(num > 5L, ExceptionConstant.验证码发送次数超限);

        String code = RandomStringUtils.randomNumeric(6);
        if (param.getState() == 1) {
            mailUtils.sendCode(param.getPhone(), code);
        } else {
            throw new CloudException(ExceptionConstant.参数异常);
//            String temp = tempMap.get(param.getType());
//            SMSUtils.send(param.getPhone(), String.format(temp, code));
        }
        log.debug("验证码为：{}，类型为：{}", code, param.getType());
        String key = param.getType() + param.getPhone();
        cloudRedisTemplate.set(key, code, 5 * 60);
        return "code";
    }

    @PostMapping(value = "/system/check/verification/code")
    @ApiOperation(value = "验证验证码")
    @Access(value = false)
    @Idempotent(timeout = 30)
    public void checkPhoneCode(String type, String account, String code) {
        AssertUtil.notBlank(type, ExceptionConstant.参数异常);
        AssertUtil.notBlank(account, ExceptionConstant.参数异常);
        AssertUtil.notBlank(code, ExceptionConstant.参数异常);
        String str = cloudRedisTemplate.get(type + account);
        AssertUtil.isTrue(StrUtil.equals(str, code), ExceptionConstant.手机验证码错误);
    }

//    @SneakyThrows
//    @PostMapping(value = "/system/get/verification/ailboxcode")
//    @ApiOperation(value = "获取邮箱验证码")
//    @Access(value = false)
//    public String getmailboxCode(@Validated VerificationCodeParam param) {
//        String code = RandomStringUtils.randomNumeric(6);
////        String temp = tempMap.get(param.getType());
//        SendEmail.mailboxCode(param.getPhone(), code);
////        SMSUtils.send(param.getPhone(), String.format(temp, code));
//        System.out.println("param.getType() :" + param.getType());
////        log.info("手机验证码为：{}，类型为：{}", code, param.getType());
//        String key = param.getType() + param.getPhone();
//        cloudRedisTemplate.set(key, code, 30 * 60);
//        return "code";
//    }

    @PostMapping("/data")
    @Access(value = false)
    //@ApiOperation(value = "藏品基础信息及行情接口")
    public List data() {
        return homeService.data();
    }

//    @PostMapping("/public/cxbh")
//    @Access(value = false)
//    @ApiOperation(value = "根据编号查询 classid")
//    public Map cxbh(String no) {
//        return homeService.cxbh(no);
//    }
//
//    @PostMapping("/public/cxqbdzyh")
//    @Access(value = false)
//    @ApiOperation(value = "查询钱包地址用户是谁")
//    public String cxqbdzyh(String no) {
//        return homeService.cxqbdzyh(no);
//    }
//
//    @PostMapping("/public/xgcpcyyh")
//    @Access(value = false)
//    @ApiOperation(value = "修改藏品持有用户")
//    public void xgcpcyyh(String no,int userid) {
//        homeService.xgcpcyyh(no,userid);
//    }
    @PostMapping("/public/Test")
    @Access(value = false)
    //@ApiOperation(value = "Test")
    public void Test() {
        homeService.test();
    }

    @GetMapping(value = "/sys/check")
    @Access(value = false)
    public void check(String phone, String xmno, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        if (StringUtil.isEmpty(phone) && StringUtil.isEmpty(xmno)) {
            StringBuilder sb = new StringBuilder();
            sb.append("tp:").append("用户手机号");
            sb.append("xmno:").append("XMNO");
            response.getWriter().write(sb.toString());
            response.getWriter().close();
        }
        response.getWriter().write(homeService.check(phone, xmno));
        response.getWriter().close();
    }

}
