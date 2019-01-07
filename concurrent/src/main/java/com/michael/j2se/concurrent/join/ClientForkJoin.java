package com.michael.j2se.concurrent.join;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 使用 fork join 实现并行计算
 */
public class ClientForkJoin {

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool();


        long startTime = System.currentTimeMillis();
        //生成一个计算任务，计算1+2+3+4
        CountTask task = new CountTask(1, 100);

        //执行一个任务
        Future<Integer> result = forkjoinPool.submit(task);

        try {
            System.out.println(result.get());

        }
        catch(Exception e) {
            System.out.println(e);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("执行时间： " + (endTime - startTime));

        long start = System.currentTimeMillis();
        Integer r = task.normalMethod(100);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + r + " , 时间：" + (end - start));

        long start2 = System.currentTimeMillis();
        Integer r2 = task.normalMethodFor(100);
        long end2 = System.currentTimeMillis();
        System.out.println("结果：" + r2 + " , 时间：" + (end2 - start2));


//        5050
//        执行时间： 1641
//        结果：5050 , 时间：10921
//        结果：5050 , 时间：11047
    }


    static class CountTask extends RecursiveTask<Integer> {
        private static final long serialVersionUID = -3611254198265061729L;

        public static final int threshold = 2;
        private int start;
        private int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;

            //如果任务足够小就计算任务
            boolean canCompute = (end - start) <= threshold;
            if(canCompute) {
                for (int i=start; i<=end; i++) {
                    sum += i;
                }
            }
            else {
                // 如果任务大于阈值，就分裂成两个子任务计算
                int middle = (start + end) / 2;
                CountTask leftTask = new CountTask(start, middle);
                CountTask rightTask = new CountTask(middle + 1, end);

                // 执行子任务
                leftTask.fork();
                rightTask.fork();

                //等待任务执行结束合并其结果
                int leftResult = leftTask.join();
                int rightResult = rightTask.join();

                //合并子任务
                sum = leftResult + rightResult;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return sum;
        }


        /**
         * 递归方法求和
         * @param n
         * @return
         */
        public Integer normalMethod(Integer n) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (n == 1) {
                return 1;
            } else if (n > 1) {
                return n + normalMethod(n - 1);
            } else {
                return 0;
            }
        }

        /**
         * 循环方法求和
         * @return
         */
        public Integer normalMethodFor(Integer n) {

            Integer sum = 0;
            for (int i = 0; i <= n; i++) {
                sum = sum + i;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return sum;
        }
    }
}
