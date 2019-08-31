package com.michael.j2se.thread.service;

import java.util.concurrent.*;

/**
 * 线程池多个返回值如何接收
 */
public class MultiReturn {


    public static void main(String[] args) throws InterruptedException, ExecutionException {


        ExecutorService executorService = new ThreadPoolExecutor(2, 2,
                0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(512),
                new ThreadPoolExecutor.DiscardPolicy());// 指定拒绝策略

        CompletionService<Result> ecs = new ExecutorCompletionService<>(executorService);// 构造器

        for (int i = 0; i < 10; i++) {
            // 注意，这里使用的是 CompletionService.submit()
            ecs.submit(() -> {
                return new Result();
            });
        }

        for (int i = 0; i < 10; i++) {
            Result result = ecs.take().get();
            System.out.println(result);
        }

    }


    static class Result {

    }



}
