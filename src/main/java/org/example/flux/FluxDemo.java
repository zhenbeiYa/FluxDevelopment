package org.example.flux;

import lombok.Data;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

/**
 * @author 23133
 * @version 1.0
 * @description: 响应式编程
 * @date 2025/1/8 11:13
 */
public class FluxDemo {
    public static void main(String[] args) {

/*        Flux<String> just = Flux.just("a", "b", "c", "d", "e", "f").doOnNext(ele->{
            ele+="!!!!";
        }).delayElements(Duration.ofSeconds(1L)).doOnCancel(()->{
            System.out.println("取消了流操作");
        }).doOnComplete(()->{
            System.out.println("结束了~~~");
        });
        //进行消费
        just.subscribe(System.out::println);*/

/*        Flux.range(1, 10).doOnEach(System.out::println).filter(i -> i > 3).map(ele -> {
//            if (ele == 9) {
//                ele = 9 / (9 - ele);
//            }
            return ele;
        }).subscribe(v -> System.out.println("v=" + v), error -> {
            System.out.println("错误信息:" + error.getMessage());
        }, () -> {
            System.out.println("完成了~~Finished");
        });*/
//        Flux<List<Integer>> range = Flux.range(1, 1000).buffer(100).log().limitRate(50);
        Flux<Integer> range = Flux.range(1, 1000).log().limitRate(50);


        range.subscribe(new BaseSubscriber<Integer>() {
            //            以下都是钩子函数
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                System.out.println("订阅发生了");
                request(1);
            }

            @Override
            protected void hookOnNext(Integer value) {
                System.out.println("数据正在处理" + value);
//                if(value==3){
//                  cancel();
//                }
                request(1);
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("数据流处理完成");
            }

            @Override
            protected void hookOnError(Throwable throwable) {
                super.hookOnError(throwable);
            }

            @Override
            protected void hookFinally(SignalType type) {
                super.hookFinally(type);
            }

            @Override
            protected void hookOnCancel() {
                System.out.println("数据流被取消执行了!!!!");
            }
        });
/*        range.subscribe(new BaseSubscriber<List<Integer>>() {
            @Override
            protected void hookOnSubscribe(Subscription subscription) {
                super.hookOnSubscribe(subscription);
            }

            @Override
            protected void hookOnNext(List<Integer> value) {
                System.out.println("value = " + value);
//                requestUnbounded();
                request(1);
            }

            @Override
            protected void hookOnComplete() {
                System.out.println("流完成了工作,Work Finished,Joy boy!!");
            }
        });*/

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
