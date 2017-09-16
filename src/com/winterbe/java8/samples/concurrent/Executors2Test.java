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

        /*
         * Лямбда - прежде всего это функциональный интерфейс <FunctionalInterface>
         *
         * У функционального интерфейса есть только один метод либо с параметрами либо без...
         * 1. Поэтому, если у нас 1-параметр - его можно указать без скобок!
         * 2. если у нас 2 и более параметров - их нужно указать в скобках!
         * 3. если у нас отсутствуют параметры - тогда нужно указать просто пустые скобки!
         * название самого метода при этом не пишем (потому-что он один и писать его незачем...)
         *
         * Дальше '->' говорим что мы переопределяем тело функции
         *
         * Потом определяем тело для этой функции:
         * - если тело функции одно-строчное, тогда можно фигурные скобки не указывать
         * - если тело функции много-строчное, тогда нужно указать фигурные скобки!
         * - если тело функции одно-строчное и без фигурных скобок, тогда будет автоматически выполнен return результата...
         */
        Future<Integer> future = executor.submit(() -> {  // Future submit(<FunctionalInterface>) // Future submit(Runnable|Callable)
            try {
                TimeUnit.SECONDS.sleep(2);
                return 0;
            } catch (InterruptedException e){
                throw new IllegalStateException("task interrupted", e);
            }
        });

        int result = -1;
//        while (!future.isDone())
            result = future.get(); // здесь 'Future' дожидается завершиения выполнения потока
//            result = future.get(1, TimeUnit.SECONDS); // java.util.concurrent.TimeoutException - изменим ограничение на время ожидания...
        System.out.println("result = " + result);
    }

    @Test //@Test(expected = ExecutionException.class)
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

        executor.shutdown();    // метод 'shutdown' дожидается завершиения выполнения потока
//        executor.shutdownNow(); // java.util.concurrent.ExecutionException
        int result = future.get();
        System.out.println("result = " + result);
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

        System.out.println("future done: " + future.isDone()); // проверяем завершения потока

        Integer result = future.get(); // дожидаемся завершения потока

        System.out.println("future done: " + future.isDone()); // проверяем завершения потока
        System.out.print("result: " + result);

        executor.shutdownNow(); // явно-неприменно прекращаем работу потока
    }
}
