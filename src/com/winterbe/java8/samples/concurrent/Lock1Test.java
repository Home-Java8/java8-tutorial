package com.winterbe.java8.samples.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.Before;

public class Lock1Test {

    private static final int NUM_INCREMENTS = 10000;
    private ReentrantLock lock;
    private int count;
    private Lock1 lock1;

    @Before
    public void init(){
        lock = new ReentrantLock();
        count = 0;
        lock1 = new Lock1();
    }

    @Test
    public void testLock(){
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, NUM_INCREMENTS)
                 .forEach(i -> executor.submit(lock1::increment));

        ConcurrentUtils.stop(executor);

        System.out.println(count);
    }


    class Lock1 {
        public void increment(){
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }
}