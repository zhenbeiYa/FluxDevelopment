package org.example;

import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author 23133
 * @version 1.0
 * @description:
 * @date 2025/1/2 15:00
 */
public class FunctionDemo {
    public static void main(String[] args) {
        Supplier<String> supplier = () -> UUID.randomUUID().toString().substring(10);
//        Supplier<String> supplier = () -> "667";
//        System.out.println(supplier.get());
        //判断supplier的随机数是否为数字
        Predicate<String> isNumber = str -> str.matches("^-?\\d+(\\.\\d+)?$");
//        System.out.println("是否为数字:"+isNumber.test(supplier.get()));
//        如果是数字直接输出打印
        if (isNumber.test(supplier.get())) {
            Function<String, Integer> change = Integer::parseInt;
            Consumer<Integer>consumer=num->{
                if(num%2==0){
                    System.out.println("该数为偶数"+num);
                }else {
                    System.out.println("该数为奇数"+num);
                }
            };
            consumer.accept(change.apply(supplier.get()));
        } else {
            System.out.println("当前字符串为:" + supplier.get());
        }
    }
}
