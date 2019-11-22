package com.seanazlin.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizationExample2 {
    private int count;

    public synchronized void increaseCount(){
        count++;
    }

    public void increaseCountSync(){
        synchronized (this){
            count++;
        }
    }

    public void performCount() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000).forEach(x -> executorService.submit(this::increaseCountSync));
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        SynchronizationExample2 syncExample = new SynchronizationExample2();
        syncExample.performCount();
    }
}
