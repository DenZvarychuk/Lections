package com.javarush.tasks.lection0211;

public class Example1 {
    public static void main(String[] args) {

        Printer printer = new Printer();
        Thread thread = new Thread(printer);
        thread.start();
        System.out.println("Printer started");
        System.out.println("Main stoped");
    }
}

class Printer implements Runnable{
    @Override
    public void run() {
        System.out.println("i'm printer");
        System.out.println("Printer thread stopped");
    }
}