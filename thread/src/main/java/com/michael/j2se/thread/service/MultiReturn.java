package com.michael.j2se.thread.service;

import lombok.*;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池多个返回值如何接收
 */
public class MultiReturn {


    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        corePoolSize -> 任务队列 -> maximumPoolSize -> 拒绝策略
        ExecutorService executorService = new ThreadPoolExecutor(20, 20,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(32),
                new ThreadPoolExecutor.DiscardPolicy()); // 指定拒绝策略

        CompletionService<Result> ecs = new ExecutorCompletionService<>(executorService); // 构造器

        int taskSize = 20;

        for (int i = 0; i < taskSize; i++) {
            // 注意，这里使用的是 CompletionService.submit()
            ecs.submit(() -> {
                TimeUnit.SECONDS.sleep(1);
                return new Result();
            });
        }

        for (int i = 0; i < taskSize; i++) {
            Result result = ecs.take().get();
            System.out.println(i);
            System.out.println(result.toString());
        }

    }


    @Getter
    @Setter
    static class Result {

        static AtomicInteger order = new AtomicInteger(0);

        private int index;

        Result() {
            index = order.getAndAdd(1);
            System.out.println(index);
        }

        public static int getOrder() {
            return order.get();
        }

        @Override
        public String toString() {
            return "Result(order = " + this.index + " )";
        }
    }

}
