package com.seanazlin.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// TODO 1: Do file homework and other examples using CompletableFuture
public class CompletableEg {
    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.completedFuture("a value 0");
        System.out.println(future.isDone());
        System.out.println(future.getNow(null));

        // Synchronous
        CompletableFuture<String> future1 = CompletableFuture.completedFuture("a value 1")
                .thenApply(x->x.toUpperCase());

        // Asynchronous
        CompletableFuture<String> future2 = CompletableFuture.completedFuture("a value 2")
                .thenApplyAsync(x->x.toUpperCase());

        System.out.println(future1.getNow(null));
        System.out.println(future2.getNow(null));
        try {
            System.out.println(future2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(()->String.valueOf(Math.random()));
        System.out.println(future3.getNow(null));
        try {
            System.out.println(future3.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        CompletableFuture<String> future4 = CompletableFuture
                .supplyAsync(()->"123")
                .thenApplyAsync(x->x + "456")
                .thenApplyAsync(x->x + "789")
                .exceptionally(ex->"Error is " + ex);
        try {
            String result = future4.handle((ok,ex)->{
                if(ok!=null) {
                    System.out.println("success: " + ok);
                    return ok;
                } else {
                    System.out.println("ex: " + ex);
                    return ex.toString();
                }
            }).get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
