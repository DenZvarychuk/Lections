package com.javarush.tasks.lection0211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExampleInterruptThread {
    public static void main(String[] args) throws IOException {
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(in);
        //create and run thread
        Stopwatch stopwatch = new Stopwatch();
        stopwatch.start();
        //read a string
        reader.readLine();
        stopwatch.interrupt();
        //close streams
        reader.close();
        in.close();
    }

    public static class Stopwatch extends Thread {
        private int seconds = 0;
        public void run() {
            while (!currentThread().isInterrupted()){
                try {
                    seconds += 1;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    currentThread().interrupt();
                    System.out.println(seconds);
                }
            }
        }
    }
}
