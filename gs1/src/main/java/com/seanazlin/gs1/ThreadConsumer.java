package com.seanazlin.gs1;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadConsumer implements Runnable{
    BlockingQueue<String> queue = null;

    public ThreadConsumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String value = queue.take();
            System.out.println("Consumer Take: " + value);
            System.out.flush();
            Thread.sleep(10000);
            System.out.println("Consumer Done: " + value);
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
