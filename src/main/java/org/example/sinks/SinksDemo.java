package org.example.sinks;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * @author 23133
 * @version 1.0
 * @description: Sinks工具类的使用
 * @date 2025/1/15 12:49
 */
public class SinksDemo {

    public static List<AtomicIntegerArray> getFluxList(int[] ints) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(ints.length);
        //堵塞式订阅获取数据
        List<AtomicIntegerArray> block = Flux.just(ints).map(ele -> {
            int n = 0;
            for (int i : ele) {
                atomicIntegerArray.set(n++, i * 10);
            }
            return atomicIntegerArray;
        }).collectList().block();
        assert Objects.nonNull(block);
        return block;
    }




    public static void main(String[] args) {
//        SinkUtils();
        //Sink的重播机制的演示
/*        Flux<Integer> cache = Flux.range(1, 7).delayElements(Duration.ofSeconds(1)).cache(3);
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cache.subscribe(System.out::println);
        }).start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
/*        List<AtomicIntegerArray> fluxList = getFluxList(new int[]{1, 555, 777, 9999, 888, 77, 444});
        fluxList.forEach(System.out::println);*/
        /*
         * 应对高并发流量要求将其转变为多线程限定数据的处理
         * */
       /* Flux.range(1,1000).buffer(100).publishOn(Schedulers.newParallel("xx")).log().parallel(8).runOn(Schedulers.newParallel("yy")).log().subscribe(v->{
            System.out.println("v = " + v);
        });*/
        //subscribeOn():订阅者并行限定
        Flux.range(1,1000000).buffer(100).publishOn(Schedulers.fromExecutor(new ThreadPoolExecutor(8,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>()))).parallel(8).runOn(Schedulers.fromExecutor(new ThreadPoolExecutor(8,10,60, TimeUnit.SECONDS,new LinkedBlockingQueue<>()))).flatMap(list -> {
         return  Flux.fromIterable(list);
        }).collectSortedList(Integer::compareTo).subscribe(v->{
            System.out.println("v = " + v);
        });
    }

    private static void SinkUtils() {
        // Sinks.Many<Object> objectMany = Sinks.many().unicast().onBackpressureBuffer(new LinkedBlockingQueue<>(5));
        //Sinks.Many<Object> objectMany = Sinks.many().multicast().onBackpressureBuffer(5);
        Sinks.Many<Object> objectMany = Sinks.many().replay().limit(5);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                    objectMany.tryEmitNext(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        objectMany.asFlux().subscribe(v -> {
            System.out.println("v1= " + v);
        });

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            objectMany.asFlux().subscribe(v -> {
                System.out.println("v2= " + v);
            });
        }).start();
    }
}
