package com.javarush.tasks.lection0210;

public class ExampleComplexRecursion {
    static void main(String[] args) {
        // x^n/(n!)
        System.out.println(calculate(3, 2));
        System.out.println(5*4*3*2*1);
    }

    private static double calculate(int x, int n) {
        if (n == 0) return 1;
        if (x == 0) return 0;
        if (x == 0) return 1;
        return power(x, n) / n;
    }

    private static double power(int x, int n) {
        if (n == 1) return x;
        return x * calculate(x, n-1);
    }

}
