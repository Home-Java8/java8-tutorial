package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.junit.Test;

public class Executors2Test {

//    @Test(expected = TimeoutException.class)
    @Test
    public void test3()
            throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(() -> {  // Future submit(Runnable|Callable)
            try {
                TimeUnit.SECONDS.sleep(2);
                return 0;
            } catch (InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        });

        int result = -1;
        while (!future.isDone())
            result = future.get(3, TimeUnit.SECONDS);
        System.out.println("result = " + result);
    }

    @Test(expected = ExecutionException.class)
    public void test2()
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 0;
            } catch (InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        });

        executor.shutdownNow();
        future.get();
    }

    @Test
    public void test1()
            throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 0;
            } catch (InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        });

        System.out.println("future done: " + future.isDone());

        Integer result = future.get();

        System.out.println("future done: " + future.isDone());
        System.out.print("result: " + result);

        executor.shutdownNow();
    }
}
