package com.michael.j2se.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class LockFair implements Runnable {
    //创建公平锁
    private static ReentrantLock lock = new ReentrantLock(true);

    // 当前的序号
    public static int currentIndex = 0;

    @Override
    public void run() {
        while (true){
            lock.lock();
            try {
                if (currentIndex < 100) {
                    System.out.println("当前值：" + currentIndex++ + ", " + Thread.currentThread().getName()+" 获得锁");
                } else {
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String []args){

        LockFair lockFairTest = new LockFair();
        Thread t1 = new Thread(lockFairTest);
        Thread t2 = new Thread(lockFairTest);
        t1.start();
        t2.start();
    }
}