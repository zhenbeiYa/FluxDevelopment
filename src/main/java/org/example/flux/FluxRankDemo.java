package org.example.flux;

import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 23133
 * @version 1.0
 * @description: 请求序列生成
 * @date 2025/1/11 9:51
 */
public class FluxRankDemo {
    //监听器
    class MyListener {
        FluxSink<Object> fluxSink;

        public MyListener(FluxSink<Object> fluxSink) {
            this.fluxSink = fluxSink;
        }

        public void online(Object username) {
            System.out.println("username = " + username);
            fluxSink.next(username);
        }
    }

    private void create() {

        Flux<Object> objectFlux = Flux.create(fluxSink -> {
            MyListener listener = new MyListener(fluxSink);
            for (int i = 0; i < 10; i++) {
                listener.online("user" + i);
            }
        });
        objectFlux.log().subscribe();

    }

    public void rank() {
        Flux<Object> generate = Flux.generate(() -> 0, (state, sink) -> {
            sink.next(state);
            if (state == 10) sink.complete();
            if (state == 7) {
                sink.error(new RuntimeException("我不喜欢吃海鲜"));
            }
            return state + 1;
        });
        generate.doOnError(e -> System.out.println("e = " + e)).subscribe(ele -> {
            System.out.println("ele = " + ele);
        }, error -> System.out.println("error = " + error));
    }

    public static void main(String[] args) {
//        new FluxRankDemo().rank();
        new FluxRankDemo().create();
    }


}
