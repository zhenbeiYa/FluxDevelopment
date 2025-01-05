package org.example;

import org.example.entity.Person;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 23133
 * @version 1.0
 * @description: 流体式编程API
 * @date 2025/1/4 10:45
 */
public class StreamDemo {

//    static int max = 0;

 /*   public static int getMaxOfOu(List<Integer> list) {
        Optional<Integer> max = list.stream().filter(f -> f % 2 == 0).max(Integer::compareTo);
        return max.get();
    }*/

    public static void main(String[] args) {
/*
        List<Integer> integers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 5, 10, 17, 25, 22, 36, 88, 96, 72, 76, 888);
//        流只允许出结果,不允许过程流出.这里是为了确保并发线程安全问题
        integers.stream().parallel().filter(ele -> ele % 2 == 0).max(Integer::compareTo).ifPresent(System.out::println);
*/
        List<Person> personList = List.of(new Person("王 舞", 28, "女"),
                new Person("华 为", 20, "男"), new Person("小 米", 23, "男"),
                new Person("V i v o", 27, "男"), new Person("雨 宫 天", 25, "女"),
                new Person("早 见 沙 织", 30, "女"), new Person("王 梦 佳", 25, "女"));
        //过滤年龄大于或等于20的女同志
  /*      personList.stream().filter(f -> f.getAge() > 20 && f.getSex().equals("女")).map(Person::getName).flatMap(fp -> {
            String[] split = fp.split(" ");
            return Arrays.stream(split);
        }).distinct().sorted(String::compareTo).forEach(System.out::println);*/
        Map<String, List<Person>> collect = personList.stream().filter(f -> f.getAge() > 20).collect(Collectors.groupingBy(Person::getSex));
        System.out.println(collect);
    }
}
