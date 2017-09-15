package com.winterbe.java8.samples.concurrent;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Semaphore1Test {

    private static final int NUM_INCREMENTS = 10000;
    private Semaphore1 semaphore1;

    @Before
    public void init(){
        semaphore1 = new Semaphore1();
    }

    @Test
    public void testIncrement() {
        // создаем 10 пулов (потоков) в которых будет выполняться операция инкрмента...
        ExecutorService executor = Executors.newFixedThreadPool(10);

        IntStream.range(0, NUM_INCREMENTS)
                .forEach(i -> executor.submit(semaphore1::increment));

        ConcurrentUtils.stop(executor);

        System.out.println("Increment: " + Semaphore1.count);
    }
}

class Semaphore1 {
    public static int count = 0;
    private Semaphore semaphore;

    {
        // но, внутри, доступ к единому ресурсу будет ограничен только 1-пулом (потоком)...
        semaphore = new Semaphore(1);
    }

    public void increment(){
        boolean acquire = false;

//        try {
//            acquire = semaphore.tryAcquire(2, TimeUnit.SECONDS);
//            count++;
//        } catch (InterruptedException e) {
//            throw new RuntimeException("could not increment");
//        } finally {
//            if (acquire){
//                semaphore.release();
//            }
//        }

        acquire = semaphore.tryAcquire();
        count++;
        if (acquire){
            semaphore.release();
        }
    }
}