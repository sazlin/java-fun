package com.seanazlin.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class SynchronizationExample4 {
    private int count = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void increaseCount(){
        try {
            lock.tryLock(5, TimeUnit.SECONDS);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void performCount() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000).forEach(x -> executorService.submit(this::increaseCount));
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        SynchronizationExample4 syncExample = new SynchronizationExample4();
        syncExample.performCount();
    }
}
