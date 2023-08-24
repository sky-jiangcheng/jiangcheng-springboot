package com.jiangc.practice.stream;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：jiangcheng
 * @version : 1.0.0
 * @description ：TODO
 * @date ： 2022/6/8 21:16
 */
public class AppleStream {

    private static Gson gson = new Gson();
    public static void testList2Map(List<Apple> appleList){

        System.out.println("testList2Map");;
        /**
         * List -> Map
         * 需要注意的是：
         * toMap 如果集合对象有重复的key，会报错Duplicate key .... *  apple1,apple12的id都为1。
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<Integer, Apple> appleMap = appleList.stream().collect(Collectors.toMap(Apple::getId, a -> a,(k1, k2)->k1));

        System.out.println(gson.toJson(appleMap));


    }

    public static void testList2GrouBy(List<Apple> appleList) {


        System.out.println("testList2GrouBy的强化应用");;
        /**
         */
        Map<String, List<Apple>> appleMap = appleList.stream().collect(Collectors.groupingBy(Apple::getName));

        System.out.println(gson.toJson(appleMap));

        List<Apple> appleList1 = new ArrayList<>();

        appleMap.keySet().forEach(name -> {
            Map<String, Long> countMap = appleMap.get(name).stream().collect(Collectors.groupingBy(apple -> apple.getColor(), Collectors.counting()));

            appleMap.get(name).stream().forEach(apple->{
                Apple apple1 = new Apple();
                apple1.setName(apple.getName());
                apple1.setColor(apple.getColor());
                apple1.setNum(countMap.get(apple.getColor()).intValue());
                appleList1.add(apple1);
            });
        });

        System.out.println(gson.toJson(appleList1));

        Map<String, Long> appleMapNew = appleList.stream().collect(Collectors.groupingBy(apple -> apple.getName() + "_" + apple.getColor(),Collectors.counting()));

        List<Apple> appleList2 = appleMapNew.keySet().stream().map(key -> {

            String[] temp = key.split("_");
            String name = temp[0];
            String color = temp[1];

            Apple apple1 = new Apple();
            apple1.setName(name);
            apple1.setColor(color);
            apple1.setNum(appleMapNew.get(key).intValue());

            return apple1;
        }).collect(Collectors.toList());

        System.out.println(gson.toJson(appleList2));

    }

    public static void testListFilter(List<Apple> appleList) {

        System.out.println("testListFilter");;
        /**
         */
        List<Apple> filteredList = appleList.stream().filter(apple -> apple.getMoney().compareTo(new BigDecimal("2.89"))>0).collect(Collectors.toList());

        System.out.println(gson.toJson(filteredList));
    }



    /**
     * map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上，这里将user对象的money取出来map为Bigdecimal
     *
     * reduce是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。可以利用它来实现很多聚合方法比如count,max,min等。
     * 这里利用了reduce的第二个方法重载
     * T reduce(T identity, BinaryOperator accumulator);
     * 第一个参数是我们给出的初值，第二个参数是累加器，可以自己用实现接口完成想要的操作，这里使用Bigdecimal的add方法
     * 最后reduce会返回计算后的结果
     */
    public static void testDecimalSum(List<Apple> appleList) {

        System.out.println("testDecimalSum");;
        /**
         */
        BigDecimal decimalSum = appleList.stream().map(apple -> apple.getMoney()).reduce(BigDecimal.ZERO,BigDecimal::add);

        System.out.println(gson.toJson(decimalSum));
    }


    public static void testCountDigits(List<Apple> appleList) {

        System.out.println("testCountDigits");;
        /**
         */
        int total = appleList.stream().mapToInt(apple -> apple.getNum()).sum();

        System.out.println(gson.toJson(total));
    }

    public static void testStrListGroupBy(List<String> items) {


        System.out.println("testStrListGroupBy");;

        Map<String, Long> result =
                items.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()
                        )
                );

        System.out.println(result);

    }
}