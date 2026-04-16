package com.javarush.tasks.lection0213.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor2 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "MyThread");
            }
        });

        for (int i = 0; i < 1_000; i++) {
            executorService.execute(new SingleThreadExecutor.Task(i));
        }
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        executorService.shutdownNow();
    }

    static class Task implements Runnable {
        private final int taskNumber;
        private static volatile boolean stopFlag = false;

        public Task(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void run() {

            if (stopFlag) {
                System.out.printf("Task #%d was skipped because stopFlag is set.\n", taskNumber);
                return;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                System.out.printf("Task #%d was interrupted.\n", taskNumber);
            }

            if (taskNumber == 5) {
                System.out.println("Stop flag is being set by task #5.");
                stopFlag = true;

            }

            System.out.printf("request #%d completed in thread id=%d\n", taskNumber, Thread.currentThread().getId());
        }
    }
}