package org.example.context;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

import java.io.IOException;
import java.time.Duration;

/**
 * @author 23133
 * @version 1.0
 * @description: reactor响应式上下文测试
 * @date 2025/1/17 9:25
 */
public class ContextDemo {


    static final String TLKEY = "prefix";

    public static void main(String[] args) {
 /*       Flux.range(1, 9).deferContextual(contextView -> Flux.just(10, 11, 15)
                .map(ele -> ele + contextView.getOrDefault(TLKEY, "Not Found")))
                .contextWrite(ctx -> ctx.put(TLKEY, "heihei")).subscribe(System.out::println);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }   */
        Flux.range(1, 9).deferContextual(contextView -> Flux.just(10, 11, 15)
                        .map(ele -> ele + "_" + contextView.getOrDefault(TLKEY, "Not Found")))
                .contextWrite(Context.of(TLKEY, "suffix")).subscribe(System.out::println);
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
