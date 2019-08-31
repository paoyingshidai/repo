package com.michael.j2se.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁，实现交替打印 1 - 100
 */
public class LockFair implements Runnable {
    //创建公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    // 当前的序号
    public static int currentIndex = 0;

    public static int currentIndex2 = 0;

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (currentIndex < 100) {
                    System.out.println("当前值：" + currentIndex++ + ", " + Thread.currentThread().getName() + " 获得锁");
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
    }


    /**
     * 交替打印 1 —— 100， 不使用 ReentranLock 公平锁;
     */
    public void print() {

        Runnable runnable  = new Runnable() {
            @Override
            public void run() {

                while(currentIndex2 <= 100) {

                    synchronized (this) {

                        notify();

                        System.out.println("当前值：" + currentIndex2++ + ", " + Thread.currentThread().getName() + " 获得锁");

                        try {
//                            TimeUnit.SECONDS.sleep(1);
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        t1.start();
        t2.start();


    }


    public static void main(String []args) {

        LockFair lockFairTest = new LockFair();

        lockFairTest.print();

//        Thread t1 = new Thread(lockFairTest);
//        Thread t2 = new Thread(lockFairTest);
//        t1.start();
//        t2.start();
    }
}