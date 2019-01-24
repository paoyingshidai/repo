package com.michael.javase.guava.base;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.Map;

public class SplitterMain {

    public static boolean hasText(String s) {
        if (s != null && s != "") {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        String joinList = "1,2,3,4";
        final List<String> sp = Splitter.on(",").splitToList(joinList);
        for (String s : sp) {
            System.out.println(s);
        }
        System.out.println();

        final Map<String, String> join = Splitter.on("&").withKeyValueSeparator("=").split("id=123&name=green");
        join.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });

//        id = 123
//        name = green
    }
}
