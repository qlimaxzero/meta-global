package com.shitouren.core.aop;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.shitouren.core.common.Constants;
import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 拦截声明了 {@link Idempotent} 注解的方法，实现幂等操作
 */
@Aspect
@Component
@Slf4j
public class IdempotentAspect {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Before("@annotation(idempotent)")
    public void beforePointCut(JoinPoint joinPoint, Idempotent idempotent) {
        String key = this.resolver(joinPoint);

        boolean success = this.setIfAbsent(key, idempotent.timeout(), idempotent.timeUnit());
        // 锁定失败，抛出异常
        if (!success) {
            log.info("[beforePointCut][方法({}) 参数({}) 存在重复请求]", joinPoint.getSignature().toString(), joinPoint.getArgs());
            throw new CloudException(ExceptionConstant.重复请求);
        }
    }

    public String resolver(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toString();
        String argsStr = StrUtil.join(",", joinPoint.getArgs());
        return SecureUtil.md5(methodName + argsStr);
    }

    public Boolean setIfAbsent(String key, long timeout, TimeUnit timeUnit) {
        String redisKey = Constants.IDEMPOTENT_KEY + key;
        return redisTemplate.opsForValue().setIfAbsent(redisKey, "", timeout, timeUnit);
    }

}
