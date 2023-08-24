package com.jiangc.practice.stream;


import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：jiangcheng
 * @version : 1.0.0
 * @description ：TODO
 * @date ： 2022/6/8 21:15
 */
public class Main {
    public static void main(String[] args) {
        List<Apple> appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果",new BigDecimal("3.25"),10,"red");
        Apple apple12 = new Apple(2,"苹果",new BigDecimal("1.35"),20,"green");
        Apple apple2 =  new Apple(3,"香蕉",new BigDecimal("2.89"),30,"red");
        Apple apple3 =  new Apple(4,"荔枝",new BigDecimal("9.99"),40,"green");

        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);

        System.out.println(new Gson().toJson(appleList));;

        AppleStream.testList2Map(appleList);

        AppleStream.testList2GrouBy(appleList);

        AppleStream.testListFilter(appleList);

        AppleStream.testDecimalSum(appleList);

        AppleStream.testCountDigits(appleList);


        List<String> items =
                Arrays.asList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");


        System.out.println(new Gson().toJson(items));;

        AppleStream.testStrListGroupBy(items);



    }
}