package com.seanazlin.gs1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class SynchronizationExample6 {
    private int count = 0;
    private StampedLock lock = new StampedLock();

//    public void increaseCount(){
//        long lockStamp = lock.writeLock();
//        try {
//            lock.tryConvertToReadLock(lockStamp);
//            count++;
//            System.out.println("count incremented");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.unlock(lockStamp);
//        }
//    }
//
//    public void getCount(){
//        try {
//            lock.readLock().lock();
//            System.out.println("count: " + count);
//            Thread.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            lock.readLock().unlock();
//        }
//    }
//
//    public static void main(String[] args){
//        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        SynchronizationExample6 syncExample = new SynchronizationExample6();
//        executorService.submit(syncExample::getCount);
//        executorService.submit(syncExample::increaseCount);
//        executorService.submit(syncExample::getCount);
//        executorService.submit(syncExample::getCount);
//    }
}
