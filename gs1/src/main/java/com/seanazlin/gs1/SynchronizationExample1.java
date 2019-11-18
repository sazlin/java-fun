package com.seanazlin.gs1;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SynchronizationExample1 {
    private int count;

    public void increaseCount(){
        count++;
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
        SynchronizationExample1 syncExample = new SynchronizationExample1();
        syncExample.performCount();
    }
}
