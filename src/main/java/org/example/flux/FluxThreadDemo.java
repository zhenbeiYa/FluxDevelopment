package org.example.flux;

import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 23133
 * @version 1.0
 * @description: Reactor自定义线程和调度器实例
 * @date 2025/1/12 13:57
 */
public class FluxThreadDemo {

    public static void thread() {
//        自定义调度器
        Flux.range(1, 7).publishOn(Schedulers.fromExecutor(new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>()))).subscribe(System.out::println);
    }

    public static void handle() {
        Flux<Object> flux = Flux.range(1, 10).handle((integer, synchronousSink) -> {
            synchronousSink.next(integer);
        });
        flux.subscribe(v -> {
            System.out.println("v = " + v);
        });

    }

    public static void main(String[] args) {
        thread();
        try {
            int c = System.in.read();
            System.out.println(c);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
