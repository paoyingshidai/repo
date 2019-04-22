package com.michael.javase.guava.base;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

public class JoinerMain {

    public static String joinString(List list, String delimiter) {

        return Joiner.on(delimiter)
                .skipNulls()
                .join(list);
    }


    public static boolean hasText(String s) {
        if (s != null && s != "") {
            return true;
        } else {
            return false;
        }
    }


    public void classLoader() {

        try {
            Class a = this.getClass().getClassLoader().loadClass("com.michael.javase.guava.base.SplitterMain");
            Class b = SplitterMain.class.getClassLoader().loadClass("com.michael.javase.guava.base.SplitterMain");



            System.out.println( a.newInstance().getClass());
            System.out.println( b.newInstance().getClass());




            if (a.newInstance() instanceof SplitterMain) {
                System.out.println("相同");
            } else {
                System.out.println("不同");
            }

            if (b.newInstance() instanceof SplitterMain) {
                System.out.println("相同");
            } else {
                System.out.println("不同");
            }


            if (a.newInstance().equals(b.newInstance())) {
                System.out.println("相同");
            } else {
                System.out.println("不同");
            }

            if (a.newInstance().equals(a.newInstance())) {
                System.out.println("相同");
            } else {
                System.out.println("不同");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        List<String> list = Lists.newArrayList("1", "2", null, "", "3");

//        list = ImmutableList.of("1");
        // 拼接 list
        // 这里的 filter 之后会产生新的 list
        list = list.stream().filter(JoinerMain::hasText).collect(Collectors.toList());
        String result = JoinerMain.joinString(list, ",");
        System.out.println(result);


        // 拼接 Map
        String re = Joiner.on("&").withKeyValueSeparator("=").join(ImmutableMap.of("id", "123", "name", "green"));
        System.out.println(re);


        JoinerMain m = new JoinerMain();

        m.classLoader();

    }

}
