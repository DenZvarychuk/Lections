package com.javarush.tasks.lection0213.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SingleThreadExecutor {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 1_000; i++) {
            executorService.execute(new Task(i));
        }
        executorService.awaitTermination(30, TimeUnit.SECONDS);
        executorService.shutdownNow();
    }

    static class Task implements Runnable {
        private final int taskNumber;

        public Task(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }

            if (taskNumber == 5) {
                Thread.currentThread().stop();
            }

            System.out.printf("request #%d completed in thread id=%d\n", taskNumber, Thread.currentThread().getId());
        }
    }
}


