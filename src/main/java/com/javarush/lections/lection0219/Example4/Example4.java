package com.javarush.lections.lection0219.Example4;

// Deadlock example

public class Example4 {
    static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        new Thread(() -> {
            synchronized (lock1) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock2) {
                    System.out.println("Work1");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock1) {
                    System.out.println("Work2");
                }
            }
        }).start();
    }
}
