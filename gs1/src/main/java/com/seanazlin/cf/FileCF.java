package com.seanazlin.cf;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class FileCF {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        Path path = FileSystems.getDefault().getPath("input.txt");
        Path path = Paths.get("input.txt");
        System.out.println(path.getFileName().toAbsolutePath());
        CompletableFuture<String> readFuture = CompletableFuture.supplyAsync(()->{
            try {
                return Files.newBufferedReader(path).lines().collect(Collectors.joining());
            } catch (Exception e) {
                e.printStackTrace();
                return "ERROR: " + e;
            }
        }).thenApply(content->{System.out.println(content); return content;});

        readFuture.get();
    }
}
