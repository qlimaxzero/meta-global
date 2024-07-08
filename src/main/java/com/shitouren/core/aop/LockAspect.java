package com.shitouren.core.aop;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.shitouren.core.config.exception.ExceptionConstant;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.utils.AssertUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 拦截声明了 {@link Lock} 注解的方法，实现幂等操作
 */
@Aspect
@Component
@Slf4j
public class LockAspect {

    @Resource
    private CloudRedisTemplate cloudRedisTemplate;

    private final SpelExpressionParser parser = new SpelExpressionParser();

    private final StandardEvaluationContext context = new StandardEvaluationContext();

    @Around("@annotation(lock)")
    public Object execute(ProceedingJoinPoint joinPoint, Lock lock) throws Throwable {
        String key = this.resolver(joinPoint, lock.prefix(), lock.key());
        log.info("lock key {}", key);
        String uuid = IdUtil.fastSimpleUUID();
        try {
            boolean rs = cloudRedisTemplate.getLock(key, uuid, lock.timeout());
            AssertUtil.isTrue(rs, ExceptionConstant.服务器限流);
            return joinPoint.proceed();
        } finally {
            boolean release = cloudRedisTemplate.releaseLock(key, uuid);
            AssertUtil.isTrue(release, ExceptionConstant.更新失败);
        }
    }

    public String resolver(ProceedingJoinPoint joinPoint, String prefix, String key) {
        String methodName = joinPoint.getSignature().toString();
        if (StrUtil.isBlank(key)) {
            key = methodName;
        } else {
            if (key.contains("#")) {
                MethodSignature signature = (MethodSignature) joinPoint.getSignature();
                key = parseExpression(key, joinPoint.getArgs(), signature.getParameterNames());
            }
        }
        return prefix + key;
    }

    private String parseExpression(String expression, Object[] args, String[] parameterNames) {
        for (int i = 0; i < args.length; i++) {
            context.setVariable(parameterNames[i], args[i]);
        }
        Expression exp = parser.parseExpression(expression);
        return exp.getValue(context, String.class);
    }

}
