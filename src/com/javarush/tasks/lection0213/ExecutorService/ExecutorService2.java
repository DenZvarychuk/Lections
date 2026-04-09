package com.javarush.tasks.lection0213;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorService2 {
    public static void main(String[] args) throws Exception {
        // example 1
        // without stop
//        // create ExecutorService with fixed 3 threads
//        ExecutorService service = Executors.newFixedThreadPool(3);
//
//        // pass a Runnable to ExecutorService
//        service.submit(() -> System.out.println("done"));

        // example 2
        // close ExecutorService with java.util.concurrent.RejectedExecutionException
//        ExecutorService service = Executors.newFixedThreadPool(3);
//        service.submit(() -> System.out.println("task 1"));
//        service.submit(() -> System.out.println("task 2"));
//        service.shutdown();
//        // here we go with RejectedExecutionException
//        service.submit(() -> System.out.println("task 3"));

        // example 3
        // number means ExecutorService successfully processed
        // toString() - tasks passed to ExecutorService but was not processed
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        List.of(1, 2, 3, 4, 5, 6, 7, 8).forEach(i -> service.submit(() -> System.out.println(i)));
//        List<Runnable> runnables = service.shutdownNow();
//        runnables.forEach(System.out::println);

        // example 4.1
        // we can spot in 3 casas
        // - shutdown(), timeout or awaitTermination()
        // true if ExecutorService before timeout and false - after
//        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.submit(() -> System.out.println("task 1"));
//        service.submit(() -> System.out.println("task 2"));
//        service.submit(() -> System.out.println("task 3"));
//        service.shutdown();
//        System.out.println(service.awaitTermination(1, TimeUnit.MICROSECONDS));

        // example 4.2
//        ExecutorService service = Executors.newFixedThreadPool(2);
//        service.submit(() -> System.out.println("task 1"));
//        service.submit(() -> System.out.println("task 2"));
//        service.submit(() -> System.out.println("task 3"));
//        System.out.println(service.isShutdown());
//        service.shutdown();
//        System.out.println(service.isShutdown());

        // example 4.3
//        ExecutorService service = Executors.newFixedThreadPool(5);
//        List.of(1, 2, 3, 4, 5, 6, 7, 8).forEach(i -> service.submit(() -> System.out.println(i)));
//        service.shutdownNow();
//        System.out.println(service.isTerminated());
    }
}
