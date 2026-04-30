package com.javarush.lections.lection0219.Example3;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.UUID;

public class Example3 {
    public static void main(String[] args) {
        Container source = new Container();
        Container target = new Container();



    }
}

class MyThread extends Thread {
    private final Container source;
    private final Container target;

    public MyThread(Container source, Container target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public void run() {
        // get source monitor
        // get target monitor
        // perform action
        // release target monitor
        // release source monitor

        synchronized (source){
            synchronized (target){
                int value = source.get();
                target.put(value);
            }
        }

    }
}


class Container{
    private final String id = UUID.randomUUID().toString();
    private final Queue<Integer> items = new ArrayDeque<>();

    public int get(){
        Integer item = items.remove();
        System.out.println("Get by: " + id + ", value" + item);
        return item;
    }

    public void put(int value){
        System.out.println("Put by: " + id + ", value" + value);
        items.add(value);
    }


}


