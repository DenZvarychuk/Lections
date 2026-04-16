package com.javarush.tasks.lection0213.threadPool;

import java.util.Collections;
import java.util.Random;
import java.util.concurrent.*;

/*
    complete tasks
*/

public class ThreadCompare {

    private static final BlockingQueue<String> QUEUE = new LinkedBlockingQueue<>();
    private static ExecutorService executor1 = Executors.newSingleThreadExecutor();
    private static ExecutorService executor2 = Executors.newCachedThreadPool();
    private static ExecutorService executor3 = Executors.newWorkStealingPool();
    private static ExecutorService executor4 = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        runProcess(executor1);
        runProcess(executor2);
        runProcess(executor3);
        runProcess(executor4);

    }

    public static void runProcess(ExecutorService consumerService) {
        System.out.println("--------------------------------------");
        simulatingProducers();
        startConsumers(consumerService);
    }

    private static void simulatingProducers() {
        int maxProdBulbs = 5_000_000;
        Random rnd = new Random();
        System.out.println("Emulate task producer...");
        System.out.println("Created: " + maxProdBulbs + " tasks...");

        QUEUE.addAll(Collections.nCopies(maxProdBulbs, rnd.toString()));

    }

    private static void startConsumers(ExecutorService consumerService) {
        System.out.println("Consumer: " + consumerService.getClass().getSimpleName());

        int queueSize = QUEUE.size();
        Consumer consumer = new Consumer();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < queueSize; i++) {
            consumerService.execute(consumer);
        }

        consumerService.shutdown();
        try {
            consumerService.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            System.out.println("Consumer complete tasks for " + (System.currentTimeMillis() - startTime) + "ms");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            String bulb = QUEUE.poll();
//            if (bulb != null) {
//                System.out.println("Task: " + bulb + "completed by consumer: " + Thread.currentThread().getName());
//            }
        }
    }
}
