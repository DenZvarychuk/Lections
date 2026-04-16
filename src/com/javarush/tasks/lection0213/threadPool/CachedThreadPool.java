package com.javarush.tasks.lection0213.threadPool;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/*
    statistic harvesting
*/

public class CachedThreadPool {

    public static ExecutorService cachedPool = Executors.newCachedThreadPool();
    public static List<Future<String>> futures;

    public static void main(String[] args) throws Exception {
        Callable<String> task = () -> {
            Thread.sleep(100);
            return "Done";
        };

        printStatistic();
        invokeMillionTasks(task);
        printStatistic();
        shutdown();
    }

    public static void invokeMillionTasks(Callable<String> task) throws Exception {
        futures = cachedPool.invokeAll(Collections.nCopies(1_000_000, task));
    }

    public static void printStatistic() {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) cachedPool;

        System.out.println("-------------------------------------------");
        System.out.println("Max number of threads in pool: "
                + pool.getLargestPoolSize());
        System.out.println("Max possible number of threads in pool: "
                + pool.getMaximumPoolSize());
        System.out.println("Current number of threads in pool: "
                + pool.getPoolSize());
        System.out.println("Planned task count: "
                + pool.getTaskCount());
        System.out.println("Completed tasks: "
                + pool.getCompletedTaskCount());
    }

    public static void shutdown() throws Exception {
        cachedPool.shutdown();
        boolean isTerminated = cachedPool.awaitTermination(100, TimeUnit.MILLISECONDS);
        if (!isTerminated) {
            cachedPool.shutdownNow();
        }
    }
}

