package com.winterbe.java8.samples.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.Before;

public class Lambda1Test {

    private List<String> names1,
            names2;

    @Before
    public void init(){
        names1 = Arrays.asList("peter", "anna", "mike", "xenia");
        names2 = Arrays.asList("peter", null, "anna", "mike", "xenia");
    }

    @Test
    public void test1(){
        // обычный способ переопределения компаратора для выполнения сортировки:
        Collections.sort(names1, new Comparator<String>(){
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        // переопределения компаратора для выполнения сортировки с помощью лямбд:
        Collections.sort(names1, (String a, String b) -> {
            return b.compareTo(a);
        });

        // пишем код красиво - вырезаем фигурные скобки и return для однострочного выражения:
        Collections.sort(names1, (String a, String b) -> b.compareTo(a));

        // пишем код красиво - лямбда умеет автоматически находить и подставлять тип параметров:
        Collections.sort(names1, (a, b) -> b.compareTo(a));

        System.out.println(names1);


        names1.sort(Collections.reverseOrder());
        System.out.println(names1);
    }

    @Test
    public void test2(){
        // пишем код красиво - (еще один способ) заменяем лмбды на прямой доступ к методу...
        names2.sort(Comparator.nullsLast(String::compareTo));
        System.out.println(names2);

        List<String> names3 = null;

        //
        Optional.ofNullable(names3)
                .ifPresent(l -> l.sort(Comparator.naturalOrder()));

        System.out.println(names3);
    }
}