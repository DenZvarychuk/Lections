package com.javarush.tasks.lection0213.ExecutorService;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExecutorService3 {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(16);
        Callable<String> task = () -> {
            Thread.sleep(1);
            return "Done";
        };

        // add 10k tasks
        List<Future<String>> futures = IntStream.range(0, 10_000)
                .mapToObj(i -> service.submit(task))
                .collect(Collectors.toList());
        System.out.printf("passed %d tasks.%n", futures.size());

        // trying to close
        service.shutdown();
        // wait 100ms
        if (service.awaitTermination(100, TimeUnit.MILLISECONDS)) {
            System.out.println("all tasks completed!");
        } else {
            // hard close
            List<Runnable> notExecuted = service.shutdownNow();
            System.out.printf("not finished %d tasks.%n", notExecuted.size());
        }

        System.out.printf("tasks that were completed %d.%n", futures.stream().filter(Future::isDone).count());
    }
}
