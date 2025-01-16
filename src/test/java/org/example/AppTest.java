package org.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void MonoTest() {
        Mono<int[]> just = Mono.just(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        just.subscribe(ele -> {
            System.out.println(Arrays.toString(ele));
        });

    }

    /**
     * @description: contactMap API test
     * @param:
     * @return: void
     * @author 23133
     * @date: 2025/1/14 9:55
     */
    @Test
    public void ContactMapTest() {
        /*       Flux.range(1, 9).concatMap(ele -> {
         *//*  return Flux.generate(() -> 0, (state, sink) -> {
                if (state == 8){
                    sink.complete();
                }
                else{
                    int pivot = state + 3;
                    sink.next(pivot);
                }
                return state + 1;
            });*//*
            return Flux.range(ele + 1, 11);
        }).subscribe(System.out::println);*/
        Flux.concat(Flux.range(1, 7), Flux.just("aaaa", "bbb")).subscribe(v->{
            System.out.println("contact_Value = " + v);
        });
//        Flux.just(1, 3, 2).concatWith(Flux.just(4, 777, 888, 99, 99999, 1100000)).subscribe(System.out::println);
    }

    @Test
    public void transform(){
        AtomicInteger atomicInteger=new AtomicInteger(0);
     /*   Flux<String> transform = Flux.just("a", "b", "c").transform(values -> {
            if (atomicInteger.incrementAndGet() == 1) {
                return values.map(String::toUpperCase);
            } else {
                return values;
            }
        });*/
        Flux<String> transform = Flux.just("a", "b", "c").transformDeferred(values -> {
            if (atomicInteger.incrementAndGet() == 1) {
                return values.map(String::toUpperCase);
            } else {
                return values;
            }
        });
        transform.subscribe(v->{
            System.out.println("订阅者1 = " + v);
        });
        transform.subscribe(v->{
            System.out.println("订阅者2 = " + v);
        });
    }

    @Test
    public void merge(){
  /*      Flux.merge(Flux.just(1,2,3),Flux.just("a","a","a")).subscribe(v->{
            System.out.println("merge_Value = " + v);

        });*/
        /*Flux.just(1,2,3).mergeWith(Flux.just(4,5,6,7)).subscribe(v->{
            System.out.println("v = " + v);
        });*/

        Flux.just(1,2,3).mergeSequential(Flux.just(4,5,6,7)).subscribe(v->{
            System.out.println("mergeSequential_v = " + v);
        });
    }
    @Test
    public void zip(){
        Flux.zip(Flux.just("a","b","c"),Flux.range(1,3)).map(tuple->{
            Integer t2 = tuple.getT2();
            String t1 = tuple.getT1();
            return  String.valueOf(t2).concat(t1);
        }).subscribe(System.out::println);
    }

}
