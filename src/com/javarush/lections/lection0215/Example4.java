package com.javarush.lections.lection0215;

public class Example4 {

    // Integer has cache (!)
    static void main(String[] args) {
        Integer a = 148;
        Integer b = 148;
        System.out.println(a==b);
        System.out.println(a.equals(b));
    }
}
