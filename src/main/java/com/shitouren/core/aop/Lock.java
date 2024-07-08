package com.shitouren.core.aop;

import com.shitouren.core.common.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 幂等注解
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    String prefix() default Constants.LOCK_KEY;

    String key() default "";

    /**
     * 锁定超时时间，默认为 5 秒
     */
    int timeout() default 5;

}
