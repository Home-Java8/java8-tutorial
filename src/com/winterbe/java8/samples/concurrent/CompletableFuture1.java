package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.Test;

public class CompletableFuture1 {

    @Test
    public void main()
            throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();

        future.complete("42");

        future.thenAccept(System.out::println)
                .thenAccept(v -> System.out.println("done"));

    }

}
