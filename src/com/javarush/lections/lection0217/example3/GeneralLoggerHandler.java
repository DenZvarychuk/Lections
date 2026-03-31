package com.javarush.lections.lection0217.example3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class GeneralLoggerHandler<T> implements InvocationHandler {

    private final T source;

    public GeneralLoggerHandler(T source) {
        this.source = source;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        long start = System.currentTimeMillis();
        Thread.sleep(1_000);

        Object result = method.invoke(source, args);

        long end = System.currentTimeMillis();
        System.out.println("Execute time: " + (end-start) + "ms");

        return result;
    }
}
