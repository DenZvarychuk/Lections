package com.javarush.lections.lection0303;

public class Calculator {
    
    public int add(int a, int b) {
        return a+b;
    }
    
    public int div(int a, int b){
        if (b == 0)
            throw new IllegalArgumentException("Division by zero is not allowed");
        return a / b;
    }
}
