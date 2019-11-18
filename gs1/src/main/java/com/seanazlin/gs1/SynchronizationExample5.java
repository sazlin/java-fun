package com.seanazlin.gs1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

public class SynchronizationExample5 {
    private int count = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void increaseCount(){
        try {
            lock.writeLock().tryLock(5, TimeUnit.SECONDS);
            count++;
            System.out.println("count incremented");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void getCount(){
        try {
            lock.readLock().lock();
            System.out.println("count: " + count);
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args){
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        SynchronizationExample5 syncExample = new SynchronizationExample5();
        executorService.submit(syncExample::getCount);
        executorService.submit(syncExample::increaseCount);
        executorService.submit(syncExample::getCount);
        executorService.submit(syncExample::getCount);
    }
}
