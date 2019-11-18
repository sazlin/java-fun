package com.seanazlin.gs1;

import lombok.Synchronized;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class SynchronizationExample3 {
    private AtomicInteger count = new AtomicInteger(0);

    public void increaseCount(){
        count.getAndIncrement();
    }

    public void increaseCountSync(){
        synchronized (this){
            count.getAndIncrement();
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
        SynchronizationExample3 syncExample = new SynchronizationExample3();
        syncExample.performCount();
    }
}
