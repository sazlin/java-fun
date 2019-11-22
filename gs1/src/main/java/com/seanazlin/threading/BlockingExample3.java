package com.seanazlin.threading;

// TODO: Producer / Consumer Problem - producer is fast, consumer is slow
// solve using plain threads

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;


public class BlockingExample3 {
    private final static int MAX_QUEUE_SIZE = 10;
    private final static ArrayList<String> queue = new ArrayList<>(MAX_QUEUE_SIZE);
    private final static ReentrantLock rLock = new ReentrantLock();
    private static int enqueueCount = 0;
    private static int dequeueCount = 0;

    private static void enqueue(String s){
        while(true) {
            try{
                rLock.lock();
                if(queue.size() < MAX_QUEUE_SIZE){
                    queue.add(s);
                    enqueueCount++;
                    System.out.println("enqueued " + s);
                    return;
                }
            } finally {
                rLock.unlock();
            }
            Thread.yield();
        }
    }

    private static String dequeue(){
        String s = null;
        while(true) {
            try {
                rLock.lock();
                if (queue.size() > 0) {
                    s = queue.remove(0);
                    dequeueCount++;
                    System.out.println("dequeued " + s);
                    return s;
                }
            } finally {
                rLock.unlock();
            }
            Thread.yield();
        }
    }

    private static void enqueueSync(String s){
        while(true) {
            synchronized(queue) {
                if (queue.size() < MAX_QUEUE_SIZE) {
                    queue.add(s);
                    enqueueCount++;
                    System.out.println("enqueued " + s);
                    return;
                }
            }
            Thread.yield();
        }
    }

    private static String dequeueSync(){
        String s = null;
        while(true) {
            synchronized(queue) {
                if (queue.size() > 0) {
                    s = queue.remove(0);
                    dequeueCount++;
                    System.out.println("dequeued " + s);
                    return s;
                }
            }
            Thread.yield();
        }
    }


    public static void main(String[] args){
        // Output total execution time on process exit
        final int WORK_COUNT = 500000;
        long start = System.currentTimeMillis();
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    System.out.println("Finished in: " + (System.currentTimeMillis() - start));
                    System.out.println("Per Item: " + ((System.currentTimeMillis() - start)/(double)WORK_COUNT));
                    System.out.println("enqueueCount: " + enqueueCount);
                    System.out.println("dequeueCount: " + dequeueCount);
                })
        );

        new Thread(()->{
            IntStream.range(0, WORK_COUNT).forEach(x -> enqueueSync(String.valueOf(x)));
        }).start();

        new Thread(()->{
            IntStream.range(0, WORK_COUNT).forEach(x -> dequeueSync());
        }).start();
    }
}
