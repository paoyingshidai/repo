package com.michael.j2se.concurrent.join;

import java.util.concurrent.*;

/**
 * 使用 fork join 实现并行计算 斐波那契算法
 */
public class ClientForkJoinFebonaqi {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkjoinPool = new ForkJoinPool();

        long start = System.currentTimeMillis();
        System.out.println(Febonaci.febonaci(43));
        long end = System.currentTimeMillis();


        //执行一个任务
        Febonaci fb = new Febonaci(43);
        long startTime = System.currentTimeMillis();
        Future<Integer> result = forkjoinPool.submit(fb);
        System.out.println(result.get());
        long endTime = System.currentTimeMillis();

        System.out.println("递归执行时间：" + (end - start));
        System.out.println("Fork 执行时间：" + (endTime - startTime));
    }


    static class Febonaci extends RecursiveTask<Integer> {
        private static final long serialVersionUID = -3611254198265061729L;

        private Integer value;

        public Febonaci(Integer value) {
            this.value = value;
        }

        @Override
        protected Integer compute() {

            if (value == 1) {
                return 1;
            } else if (value == 2) {
                return 1;
            } else if (value > 2) {
                Febonaci febonaci = new Febonaci(value - 1);
                Febonaci febonaci2 = new Febonaci(value - 2);
                febonaci.fork();
                febonaci2.fork();

                return febonaci.join() + febonaci2.join();
            } else {
                return 0;
            }
        }

        public static Integer febonaci(Integer value) {

            if (value == 1) {
                return 1;
            } else if (value == 2) {
                return 1;
            } else if (value > 2) {
                return febonaci(value - 1) + febonaci(value - 2);
            } else {
                return 0;
            }
        }

    }
}
