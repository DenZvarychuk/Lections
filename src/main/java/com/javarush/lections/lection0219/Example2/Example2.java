package com.javarush.lections.lection0219.Example2;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Example2 {
    public static void main(String[] args) {
        Buffer<Integer> integerBuffer = new Buffer<>(10);
        Thread thread1 = new Thread(new Consumer<>(integerBuffer, 1000));
        Thread thread2 = new Thread(new Producer<>(integerBuffer, () -> (int) System.currentTimeMillis(), 1000));

        thread2.start();
        thread1.start();

    }
}


class Buffer<T> {
    private final int maxSize;
    private final Queue<T> items;
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        this.items = new ArrayDeque<>();
    }

    public void put(T item) {
        lock.lock();
        try {
            while (items.size() == maxSize) {
                notFull.await();
            }
            items.add(item);
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public T getItems() {
        lock.lock();
            try {
                while (items.isEmpty()) {
                    notEmpty.await();
                }
                T item = items.remove();
                notFull.signal();
                return item;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
            lock.unlock();
            }
    }
}

interface Factory<T> {
    T create();
}

class Producer<T> implements Runnable {
    private final Buffer<T> buffer;
    private final Factory<T> factory;
    private final int numOfIterations;
    private final String id;

    public Producer(Buffer<T> buffer, Factory factory, int numOfIterations) {
        this.buffer = buffer;
        this.factory = factory;
        this.numOfIterations = numOfIterations;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        for (int i = 0; i < numOfIterations; i++) {
            buffer.put(factory.create());
            System.out.println("Producer: " + id + ", iteration #" + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer<T> implements Runnable {
    private final Buffer<T> buffer;
    private final int numOfIterations;
    private final String id;

    public Consumer(Buffer<T> buffer, int numOfIterations) {
        this.buffer = buffer;
        this.numOfIterations = numOfIterations;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public void run() {
        for (int i = 0; i < numOfIterations; i++) {
            T item = buffer.getItems();
            System.out.println("Consumer: " + id + ", item: " + item);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

