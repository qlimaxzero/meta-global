package com.shitouren.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class TransactionProcessor {

    /**
     *  only for insertupdate ops
     * @param r
     */

    @Transactional(rollbackFor = Exception.class)
    public void doTransaction(ThrowExceptionRunnable r) {
        log.info("start transaction");
        try {
            r.run();
        }catch (Exception e){
            throw new RuntimeException("do transaction failed");
        }
        log.info("end transaction");
    }

    @FunctionalInterface
    public interface ThrowExceptionRunnable {
        void run() throws Exception;
    }

    @FunctionalInterface
    public interface ThrowExceptionSupplier<T> {
        T get() throws Exception;
    }

    @FunctionalInterface
    public interface ThrowExceptionBiFunction<T, U, R> {
        R apply(T t, U u) throws Exception;
    }

    @FunctionalInterface
    public interface ThrowExceptionFunction<T, R> {
        R apply(T t) throws Exception;
    }
}
