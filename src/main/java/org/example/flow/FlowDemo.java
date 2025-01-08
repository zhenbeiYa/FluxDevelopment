package org.example.flow;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * @author 23133
 * @version 1.0
 * @description: Reactive Coding Style demo
 * @date 2025/1/6 9:37
 */
public class FlowDemo {

    static class CustomizeProcessor extends SubmissionPublisher<String> implements Flow.Processor<String, String> {


        private Flow.Subscription subscription;

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            System.out.println("processor订阅成功");
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(String item) {
            item += "!!!!";
            submit(item);
            subscription.request(1);
        }

        @Override
        public void onError(Throwable throwable) {

        }

        @Override
        public void onComplete() {

        }
    }

    /*
     * Publisher 发布者
     * Subscriber 订阅者
     * Subscription 订阅关系
     * Processor  处理器 中间流操作
     * */
    public static void main(String[] args) {
        SubmissionPublisher<String> submissionPublisher = new SubmissionPublisher<>();
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            //正在订阅  进行时
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println( "正在订阅" + subscription);
                this.subscription = subscription;
                subscription.request(1);
            }

            //元素到达时的回调
            @Override
            public void onNext(String item) {
                System.out.println("所收到的数据" + item);
                if (item.equals("8")) {
                    //取消订阅
                    subscription.cancel();
                    System.out.println("取消订阅");
                } else {
                    subscription.request(1);
                }
            }

            //发生错误时的回调
            @Override
            public void onError(Throwable throwable) {

            }

            //订阅完成时的回调
            @Override
            public void onComplete() {
                System.out.println("订阅完成" + subscription);
            }
        };
        //绑定关系
//        submissionPublisher.subscribe(subscriber);
        CustomizeProcessor customizeProcessor = new CustomizeProcessor();
        submissionPublisher.subscribe(customizeProcessor);
        customizeProcessor.subscribe(subscriber);
        for (int i = 0; i < 10; i++) {
//              BufferedSubscription<T> b = clients;  缓冲端
            submissionPublisher.submit(String.valueOf(i));
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
