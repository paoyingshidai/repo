package com.michael.j2se.concurrent.join;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

public class ClientCalllable {


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Vector<Future> futures = new Vector<>(10);
        List results = new ArrayList(10);
        long nowTime = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {

            Future<String> result = executorService.submit(() -> {
                Thread.sleep(1000);
                return "string";
            });
            futures.add(result);
        }

        Future<String> result = executorService.submit(() -> {
            Thread.sleep(10000);
            throw new Exception("异常");
//            return "string";
        });
        futures.add(result);

        futures.forEach(future -> {
            try {
                results.add(future.get(5, TimeUnit.SECONDS));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("异常" + e.getMessage());
            }
        });

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - nowTime);

        results.forEach(r -> {
            System.out.println(r);
        });
    }
}
