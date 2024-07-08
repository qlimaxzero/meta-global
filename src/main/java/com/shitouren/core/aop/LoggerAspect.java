package com.shitouren.core.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
// @Aspect
// @Component
public class LoggerAspect {
  public static final String LOG_TEMPLATE =
    "class: %s, method: %s, inputs: %s, output: %s, elapsed: %dms";

  public LoggerAspect() {
  }

  @Pointcut("@annotation(MethodLog)")
  public void loggerPointcut() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
  public void loggerPointcutPostMapping() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
  public void loggerPointcutGetMapping() {
  }

  @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
  public void loggerPointcutRequestMapping() {
  }

  @Around("loggerPointcut()")
  public Object updateAdvice(ProceedingJoinPoint pjp) throws Throwable {
    return this.updateAdvice0(pjp);
  }

  @Around("loggerPointcutPostMapping()")
  public Object updateAdvicePostMapping(ProceedingJoinPoint pjp) throws Throwable {
    return this.updateAdvice0(pjp);
  }

  @Around("loggerPointcutGetMapping()")
  public Object updateAdviceGetMapping(ProceedingJoinPoint pjp) throws Throwable {
    return this.updateAdvice0(pjp);
  }

  @Around("loggerPointcutRequestMapping()")
  public Object updateAdviceRequestMapping(ProceedingJoinPoint pjp) throws Throwable {
    return this.updateAdvice0(pjp);
  }

  private Object updateAdvice0(ProceedingJoinPoint pjp) throws Throwable {
    Object[] inputs  = pjp.getArgs();
    String   request = "";

    try {

      request = Arrays.stream(inputs)
                      .map((input) -> {
                        try {
                          return input == null ? "[null Object]" : JSON.toJSONString(input);
                        } catch (Exception e) {
                          // log.warn("error occurs when logging controller activities.", e);
                          return "[error when serialization]";
                        }
                      })
                      .collect(Collectors.joining(","));
    } catch (Exception e) {
      log.warn("error occurs when logging controller activities.", e);
    }

    long   ts      = System.currentTimeMillis();
    Object obj     = pjp.proceed();
    long   elapsed = System.currentTimeMillis() - ts;

    try {
      String response;
      if (obj == null) {
        response = "[null Object]";
      } else {
        if (obj instanceof Collection) {
          Collection<?> c = (Collection<?>) obj;
          response = String.format("[Collection size of %d]", c.size());
        } else {
          response = JSON.toJSONString(obj);
        }
      }
      log.info(String.format(LOG_TEMPLATE,
                             pjp.getSignature()
                                .getDeclaringTypeName(),
                             pjp.getSignature()
                                .getName(),
                             request,
                             response,
                             elapsed));
    } catch (Exception e) {
      log.warn("error occurs when logging controller activities.", e);
    }
    return obj;
  }
}
