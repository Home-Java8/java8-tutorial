//package com.winterbe.java8.samples.concurrent;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.locks.ReentrantLock;
//import org.junit.Test;
//import org.junit.Before;
//
//public class Lock2Test {
//
//    private ReentrantLock lock;
//
//    @Before
//    public void init(){
//        lock = new ReentrantLock();
//    }
//
//    @Test
//    public void test(){
//        ExecutorService executor = Executors.newFixedThreadPool(2);
//
//        executor.submit(() -> {
//            lock.lock();
//            try {
//                ConcurrentUtils.sleep(1);
//            } finally {
//                lock.unlock();
//            }
//        });
//
//        executor.submit(() -> {
//            System.out.println("Locked: " + lock.isLocked());
//            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
//            System.out.println("Lock acquired: " + lock.tryLock());
//        });
//
//        ConcurrentUtils.stop(executor);
//    }
//}
