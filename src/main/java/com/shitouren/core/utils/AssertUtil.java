package com.shitouren.core.utils;

import com.shitouren.core.config.exception.CloudException;
import com.shitouren.core.config.exception.ExceptionConstant;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class AssertUtil {

    public static void isTrue(boolean exp, ExceptionConstant e) {
        if (!exp) {
            throw new CloudException(e);
        }
    }

    public static void isFalse(boolean exp, ExceptionConstant e) {
        if (exp) {
            throw new CloudException(e);
        }
    }

    public static void notNull(Object o, ExceptionConstant e) {
        if (o == null) {
            throw new CloudException(e);
        }
    }

    public static void isNull(Object o, ExceptionConstant e) {
        if (o != null) {
            throw new CloudException(e);
        }
    }

    public static void notBlank(String o, ExceptionConstant e) {
        if (o == null || o.trim().isEmpty()) {
            throw new CloudException(e);
        }
    }

    public static void notEmpty(Collection<?> collection, ExceptionConstant e) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new CloudException(e);
        }
    }

    public static void isEmpty(Collection<?> collection, ExceptionConstant e) {
        if (!CollectionUtils.isEmpty(collection)) {
            throw new CloudException(e);
        }
    }

}
