package com.seanazlin.threading;

// TODO: Producer / Consumer Problem - producer is fast, consumer is slow
// solve using plain threads

import com.seanazlin.threading.ThreadConsumer;
import com.seanazlin.threading.ThreadProducer;

import java.util.concurrent.ArrayBlockingQueue;


// TODO: Review example
public class BlockingExample {
    public static void main(String[] args){
        ArrayBlockingQueue<String> bQ = new ArrayBlockingQueue<String>(10, true);
        for(int i = 0; i<20; i++){
            ThreadProducer producer = new ThreadProducer(bQ);
            new Thread(producer).start();
        }

        for(int i = 0; i<20; i++){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ThreadConsumer consumer = new ThreadConsumer(bQ);
            new Thread(consumer).start();
        }
    }
}
