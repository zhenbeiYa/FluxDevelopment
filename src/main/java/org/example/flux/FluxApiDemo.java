package org.example.flux;

import org.example.error.BusinessException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author 23133
 * @version 1.0
 * @description: FLux开发常用API操作
 * @date 2025/1/14 9:33
 */
public class FluxApiDemo {

    /**
     * @description: reactor响应式编程的错误处理
     * @param:
     * @return: void
     * @author 23133
     * @date: 2025/1/15 9:34
     */
    public static void error() {
        //采用订阅者直接感知处理错误的方式
 /*       Flux.range(1,8).map(ele-> 10/(ele-7)
        ).subscribe(ele->{
            System.out.println("ele = " + ele);
        },error->{
            System.out.println("error = " + error.getMessage());
        });*/
        //采用回调感知处理,通过对于错误发生进行回调处理
   /*     Flux.range(1,8).map(ele-> 10/(ele-7)
        ).onErrorReturn(-1).subscribe(System.out::println);
        */
        //采用回调方法返回流进行错误处理
 /*       Flux.range(1,8).map(ele-> 10/(ele-7)
        ).onErrorResume(throwable -> {
           return Mono.just(-1);
        }).subscribe(System.out::println);*/
        //
     /*   Flux.just(1, 2, 3,0).map(ele -> "100/" + ele + "=" + (100 / ele)).onErrorResume(FluxApiDemo::handler).subscribe(v -> {
            System.out.println("v = " + v);
        });  */
        //通过利用自定义方法进行错误处理
      /*  Flux.just(1, 2, 3,0).map(ele -> "100/" + ele + "=" + (100 / ele)).onErrorResume(FluxApiDemo::handler).subscribe(v -> {
            System.out.println("v = " + v);
        });*/
        //通过自定义异常方法进行错误错误
        Flux.just(1, 2, 3,0).map(ele -> "100/" + ele + "=" + (100 / ele)).onErrorResume(e->Flux.error(new BusinessException(e))).subscribe(v -> {
            System.out.println("v = " + v);
        });
    }

    public static Mono<String> handler(Throwable throwable) {
        if (throwable.getClass() == ArithmeticException.class) {
            return Mono.just(throwable.getMessage());
        }
        return Mono.just("~~~~~~~~~~~~~~~~");
    }

    public static void main(String[] args) {
        error();
    }
}
