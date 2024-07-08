package com.shitouren.core.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

// @Aspect
// @Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.shitouren.core.aop.Master) " +
            "&& (execution(* com.shitouren.core.autogenerate.dao.*.select*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.get*(..))" +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.count*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.shitouren.core.aop.Master) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.insert*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.add*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.update*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.edit*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.delete*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.increase*(..)) " +
            "|| execution(* com.shitouren.core.autogenerate.dao.*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }


    /**
     * 另一种写法：if...else...  判断哪些需要读从数据库，其余的走主数据库
     */
//    @Before("execution(* com.cjs.example.service.impl.*.*(..))")
//    public void before(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//
//        if (StringUtils.startsWithAny(methodName, "get", "select", "find")) {
//            DBContextHolder.slave();
//        }else {
//            DBContextHolder.master();
//        }
//    }
}
