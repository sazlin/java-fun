package com.seanazlin.threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
With one thread it took 69.6 ns per square
With four threads it took 844.5 ns per square
 */
public class ThreadExperiment1 {
    public static void main(String[] args){
        final double[] results = new double[10*1000*1000];
        {
            long start = System.nanoTime();
            // using a plain loop.
            for(int i=0;i<results.length;i++) {
//                results[i] = (double) i * i;
                results[i] = Math.pow(i%4, 1.5);
            }
            long time = System.nanoTime() - start;
            System.out.printf("With one thread it took %.1f ns per square%n", (double) time / results.length);
        }
        {
            ExecutorService ex = Executors.newFixedThreadPool(4);
            long start = System.nanoTime();
            // using a plain loop.
            for(int i=0;i<results.length;i++) {
                final int i2 = i;
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
//                        results[i2] = i2 * i2;
                        results[i2] = Math.pow(i2%4, 1.5);

                    }
                });
            }
            ex.shutdown();
            try {
                ex.awaitTermination(1, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long time = System.nanoTime() - start;
            System.out.printf("With four threads it took %.1f ns per square%n", (double) time / results.length);
        }
    }
}
