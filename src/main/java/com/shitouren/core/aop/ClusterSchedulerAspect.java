package com.shitouren.core.aop;

import cn.hutool.core.util.StrUtil;
import com.shitouren.core.common.Constants;
import com.shitouren.core.config.redis.CloudRedisTemplate;
import com.shitouren.core.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.lang.reflect.Method;

@Aspect
@Component
@Slf4j
public class ClusterSchedulerAspect {

    @Resource
    private CloudRedisTemplate cloudRedisTemplate;

    private String hostIp;

    @PostConstruct
    public void init() {
        hostIp = IpUtils.getLocalHostIp();
        log.info("init scheduling task machine, redis key {} {}", Constants.SCHEDULING_KEY, hostIp);
        cloudRedisTemplate.setnx(Constants.SCHEDULING_KEY, hostIp);
    }

    @PreDestroy
    public void destroy() {
        if (StrUtil.equals(cloudRedisTemplate.get(Constants.SCHEDULING_KEY), hostIp)) {
            log.info("release scheduling task machine, redis key {} {}", Constants.SCHEDULING_KEY, hostIp);
            cloudRedisTemplate.delete(Constants.SCHEDULING_KEY);
        }
    }

    @Pointcut("@within(com.shitouren.core.aop.ClusterScheduler)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object around0(ProceedingJoinPoint joinPoint) throws Throwable {
        String v = cloudRedisTemplate.get(Constants.SCHEDULING_KEY);
        if (StrUtil.equals(v, hostIp)) {
            return joinPoint.proceed();
        } else {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            log.warn("repeat scheduling task[{}], current {} local {}", method.getName(), v, hostIp);
            return null;
        }
    }

}
