package com.seanazlin.gs1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
With one thread it took 67.2 ns per pow 1.5
With four threads it took 32.9 ns per pow 1.5
 */
public class ThreadExperiment1Better {
    public static void main(String[] args) {
        final double[] results = new double[10 * 1000 * 1000];
        {
            long start = System.nanoTime();
            // using a plain loop.
            for (int i = 0; i < results.length; i++) {
//                results[i] = (double)i * i;
                results[i] = Math.pow(i%4, 1.5);
            }
            long time = System.nanoTime() - start;
            System.out.printf("With one thread it took %.1f ns per pow 1.5%n", (double) time / results.length);
        }
        {
            int threads = 4;
            ExecutorService ex = Executors.newFixedThreadPool(threads);
            long start = System.nanoTime();
            int blockSize = results.length / threads;
            // using a plain loop.
            for (int i = 0; i < threads; i++) {
                final int istart = i * blockSize;
                final int iend = (i + 1) * blockSize;
                ex.execute(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = istart; i < iend; i++)
//                            results[i] = (double) i * i;
                            results[i] = Math.pow(i%4, 1.5);
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
            System.out.printf("With four threads it took %.1f ns per pow 1.5%n", (double) time / results.length);
        }
    }
}
