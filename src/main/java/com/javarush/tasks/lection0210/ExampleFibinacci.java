package com.javarush.tasks.lection0210;

public class ExampleFibinacci {
    public static void main(String[] args) {
        System.out.println(0);
        System.out.println(1);
        printFibonacciWithCondition(0, 1);
    }

    private static void printFibonacciWithCondition(long penultimate, long previous) {
        long current = penultimate + previous;
        if (current > 24) {
            return;
        }
        echo(true, penultimate, previous);
        System.out.println(current);
        printFibonacciWithCondition(previous, current);
        echo(false, penultimate, previous);
    }

    private static void echo(boolean isBeforeRecursiveCall, long penultimate, long previous) {
        if (isBeforeRecursiveCall) {
            System.out.printf("Before method call with args: %d, %d. Current number = ", penultimate, previous);
        } else {
            System.out.printf("After  method call with args: %d, %d\n", penultimate, previous);
        }
    }
}
