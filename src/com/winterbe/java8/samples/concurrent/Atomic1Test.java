package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.Before;

public class Atomic1Test {

    private static final int NUM_INCREMENTS = 1000;

    private AtomicInteger atomicInt;

    @Before
    public void init(){
//        atomicInt = new AtomicInteger(0);
        atomicInt = new AtomicInteger();
        atomicInt.set(0);
    }

    /**
     * Update
     */
    @Test
    public void testUpdate(){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.updateAndGet(n -> n+2);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Update: %d\n", atomicInt.get());
    }

    /**
     * Accumulate
     */
    @Test
    public void testAccumulate(){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> {
                    Runnable task = () ->
                            atomicInt.accumulateAndGet(i, (n, m) -> n+m);
                    executor.submit(task);
                });

        ConcurrentUtils.stop(executor);

        System.out.format("Accumulate: %d\n", atomicInt.get());
    }

    /**
     * Increment
     */
    @Test
    public void testIncrement(){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(atomicInt::incrementAndGet));

        ConcurrentUtils.stop(executor);

        System.out.format("Increment: Expected=%d; Is=%d\n", NUM_INCREMENTS, atomicInt.get());
    }
}
