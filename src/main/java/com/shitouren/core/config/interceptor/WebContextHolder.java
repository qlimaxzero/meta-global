package com.shitouren.core.config.interceptor;

public class WebContextHolder {

    private static final ThreadLocal<Integer> LOCAL = new ThreadLocal<>();

    public static void init() {
        if (LOCAL.get() != null) LOCAL.remove();
    }

    public static void set(Integer uid) {
        LOCAL.set(uid);
    }

    public static Integer get() {
        return LOCAL.get();
    }

}
