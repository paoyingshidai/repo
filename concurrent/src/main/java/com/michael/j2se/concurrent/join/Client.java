package com.michael.j2se.concurrent.join;

import java.util.Vector;

public class Client {


    public static void main(String[] args) {


        Vector<Thread> threads = new Vector<>(10);
        long nowTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {

            Thread t = new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

            threads.add(t);
            t.start();
        }



        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
                throw new Exception("异常");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        threads.add(t);
        t.start();


        threads.forEach(th -> {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long endTime = System.currentTimeMillis();

        System.out.println(endTime - nowTime);

    }


}
