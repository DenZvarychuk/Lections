package com.javarush.lections.lection0217.example1;

import java.lang.reflect.Field;

public class Example1 {
     // change private field value using Reflection API
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        A a = new A();
        System.out.println(a.getValue());
        Class<? extends A> aClass = a.getClass();
        Field field = aClass.getDeclaredField("value");

        field.setAccessible(true);
        System.out.println(field.getType());
        field.setInt(a, 123);
        System.out.println(a.getValue());
        field.set(a, 213);
        System.out.println(a.getValue());
    }
}

class A{
    private int value;
    private String str;

    public int getValue(){
        return value;
    }
}