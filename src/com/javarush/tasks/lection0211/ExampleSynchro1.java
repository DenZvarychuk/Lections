package com.javarush.tasks.lection0211;

import java.util.concurrent.locks.LockSupport;

public class ExampleSynchro1 {

    static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        // task будет ждать, пока его не оповестят через lock
        Runnable task = () -> {
            //Запаркуем текущий поток
            System.err.println("Will be Parked");
            LockSupport.park();
            // Как только нас распаркуют - начнём действовать
            System.err.println("Unparked");
        };
        Thread th = new Thread(task);
        th.start();
        Thread.currentThread().sleep(2000);
        System.err.println("Thread state: " + th.getState());

        LockSupport.unpark(th);
        Thread.currentThread().sleep(2000);
    }

}
