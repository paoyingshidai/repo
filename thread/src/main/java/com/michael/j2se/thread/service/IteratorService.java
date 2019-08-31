package com.michael.j2se.thread.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IteratorService {


    public static void main(String[] args) {

        List<Integer> collect = Stream.of(1, 2, 3, 4, 5).collect(Collectors.toList());

        Iterator<Integer> iterator = collect.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();                  // 可以移除
//            collect.remove(iterator.next());    // 不可以移除
        }

    }

}
