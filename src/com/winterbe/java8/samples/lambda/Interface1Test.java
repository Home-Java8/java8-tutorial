package com.winterbe.java8.samples.lambda;

import org.junit.Test;

public class Interface1Test {

    /*
     * Интерфейс может содержать:
     * 1. абстрактные методы
     * 2. метод по умолчанию
     * 3. статические методы
     *
     * - лямбда автоматически будет переопределять метод по умолчанию
     */
    interface Formula {
        double calculate(int a);

        default double sqrt(int a){
            return Math.sqrt(positive(a));
        }

        static int positive(int a){
            return 0<a ? a : 0; // если 0 < 'a' тогда возвращаем 'a', во всех остальных случаях возвращаем 0.
        }
    }

    @Test
    public void test(){
        Formula formula1 = new Formula(){
            @Override
            public double calculate(int a) {
                return sqrt(a*100); // получить квадратный корень из 100 в квадрате!
            }
        };

        System.out.println( formula1.calculate(100) ); // 100.0
        System.out.println( Formula.positive(-4) );    // 0
        System.out.println( formula1.sqrt(-23) );      // 0.0
        System.out.println( formula1.sqrt(100) );      // 10.0

        // лямбда автоматически переопределяtт метод по умолчанию:
//        Formula formula2 = (a) -> sqrt( a * 100);
        Formula formula2 = (a) -> a+100; //Formula formula2 = (a) -> { return (a+100); }; // если тело функции одно-строчное и без фигурных скобок, тогда будет автоматически выполнен return результата
        System.out.println( formula2.calculate(100) ); // 200.0
    }

}