package org.example;

import java.util.function.Function;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Function<String, Integer> function = Integer::parseInt;
        System.out.println(function.apply("2"));
        System.out.println("Hello World!");
    }
}
