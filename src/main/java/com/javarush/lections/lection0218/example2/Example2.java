package com.javarush.lections.lection0218.example2;

import java.util.List;

public class Example2 {
    static void main(String[] args) {
        print("hello");
    }

    @SuppressWarnings("java:S106")
    private static void print(String message){
        System.out.println(message);
    }

    @SafeVarargs
    public Example2(List<String>...args){
        System.out.println(args);
    }

}

class Car{
    public void drive(){}

    @Deprecated(since = "1", forRemoval = true)
    public void fix(){}
}

class SuperCar extends Car{
    @Override
    public void drive() {
        System.out.println("driven");
    }
}

@FunctionalInterface
interface MyFunction{
    int convert(String value);
}

