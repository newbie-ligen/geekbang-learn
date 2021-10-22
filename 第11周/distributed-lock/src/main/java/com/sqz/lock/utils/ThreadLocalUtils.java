package com.sqz.lock.utils;

/**
 *
 **/
public class ThreadLocalUtils {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void put(String key) {
        threadLocal.set(key);
    }

    public static String get() {
        return threadLocal.get();
    }

}
