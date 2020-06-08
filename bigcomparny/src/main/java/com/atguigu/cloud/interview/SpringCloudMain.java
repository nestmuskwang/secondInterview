package com.atguigu.cloud.interview;

import java.util.*;

/**
 * @author ：wangsg
 * @date ： 2020/5/22 9:54
 */

public class SpringCloudMain {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        Arrays.sort(list.toArray());
        Map<String, Object> map = new HashMap<>(3);
        map.put("1","2");
        map.put("2","2");
        map.put("3","2");
        map.put("4","2");
        for (String s : map.keySet()) {
            System.out.println(s);
            System.out.println(map.get(s));
        }

    }
}
