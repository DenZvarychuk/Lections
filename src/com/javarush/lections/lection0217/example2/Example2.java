package com.javarush.lections.lection0217.example2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Example2 {
    static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class<B> bClass = B.class;

        Field staticValue = bClass.getDeclaredField("staticValue");
        staticValue.setAccessible(true);
        staticValue.setInt(null, 321);

        Field[] declaredFields = bClass.getDeclaredFields();
        for (var field : declaredFields){
            System.out.println(field.getName());
        }

        Method getStaticValue = bClass.getDeclaredMethod("getStaticValue");
        getStaticValue.setAccessible(true);
        System.out.println(getStaticValue.invoke(null));

        Method[] declaredMethods = bClass.getDeclaredMethods();
        for (var method : declaredMethods) {
            System.out.println(method.getName());
        }

        Constructor<B> declaredConstructor = bClass.getDeclaredConstructor(int.class);
        declaredConstructor.setAccessible(true);
        B b = declaredConstructor.newInstance(987);
        System.out.println(b.getValue());

    }
}

class B{
    private static int staticValue;
    private final int value;

    private B(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static int getStaticValue() {
        return staticValue;
    }
}