package com.seanazlin.threading;

import java.util.concurrent.BlockingQueue;

public class ThreadProducer implements Runnable{
    BlockingQueue<String> queue = null;

    public ThreadProducer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            String value = Math.random() + "";
            System.out.println("Producer About to Queue: " + value);
            System.out.flush();
            queue.put(value);
//            Thread.sleep(1000);
            System.out.println("Producer Queued: " + value);
            System.out.println("Queue size: " + queue.size());
            System.out.flush();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
