package com.javarush.tasks.lection0211;

public class ExampleSynchro2 {

    static void main(String[] args) {
        Counter counter = new Counter();

        Runnable task = () -> Counter.dowork(counter);

        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");

        t1.start();
        t2.start();
    }
}

class Counter {
    int value = 0;
    int turn = 1;

    public static void dowork(Counter counter){
        int threadId = Thread.currentThread().getName().equals("T1") ? 1 : 2;

        while (counter.value < 14) {
            synchronized (counter) {
                while (counter.turn != threadId) {
                    try {
                        counter.wait();
                    } catch (InterruptedException e) {
                        return;
                    }
                }
                for (int i = 0; i < 1; i++) {
                    counter.value++;
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + counter.value);
                }

                counter.turn = threadId == 1 ? 2 : 1;

                counter.notifyAll();
            }
        }
    }

}
