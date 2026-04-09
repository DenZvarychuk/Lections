package com.javarush.tasks.lection0213;
import java.util.List;
import java.util.concurrent.*;
/**
 * output:
 * "done" - threads created
 * after 6s - invokeAll called, and returned Future - "got future"
 * after 2 threads been executed, another 2 starts,
 * and they canceled by invokeAll - "sleep 1: sleep interrupted"
 * memory leaks - we have "done"
 * 5th task has not been started,
 * and we have isShutdown and isTerminated "true"
 * first 2 tasks are Completed normally, another 3 are Canceled
  */
public class ExecutorService {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
//create ExecutorService for 2 threads
        java.util.concurrent.ExecutorService executorService = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
//create 5 tasks
        MyRunnable task1 = new MyRunnable();
        MyRunnable task2 = new MyRunnable();
        MyRunnable task3 = new MyRunnable();
        MyRunnable task4 = new MyRunnable();
        MyRunnable task5 = new MyRunnable();

        final List<MyRunnable> tasks = List.of(task1, task2, task3, task4, task5);
//send tasks to execute
        final List<Future<Void>> futures = executorService.invokeAll(tasks, 6, TimeUnit.SECONDS);
        System.out.println("got futures");

//stop the ExecutorService
        executorService.shutdown();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(executorService.isShutdown());
        System.out.println(executorService.isTerminated());
    }

    public static class MyRunnable implements Callable<Void> {

        @Override
        public Void call() {
// Add 2 time delays. While stops ExecutorService will check which task will be executed while attempting to stop
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println("sleep 1: " + e.getMessage());
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("sleep 2: " + e.getMessage());
            }
            System.out.println("done");
            return null;
        }
    }
}
