package com.guigu.basis.controller;

import java.util.*;

/**
 * @Title:
 * @Description: 给定一个整数数组，找出出现奇数次的整数。
 * 总是只有一个整数出现奇数次。
 * @Author Ning
 * @Date
 * @Version 0.0.1˚
 * @Company:
 **/

public class TestDemo {

    public static void main(String[] args) {
        int[] b = {3, 4, 4, 10, 10, 10, 11};
        Map<Integer, Integer> map1 = new HashMap<Integer, Integer>();
        for (int i = 0; i < b.length; i++) {
            if (map1.containsKey(b[i])) {
                map1.put(b[i], map1.get(b[i]) + 1);
            } else {
                map1.put(b[i], 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (entry.getValue() % 2 > 0) {
                System.out.println(entry.getKey());
            }
        }


        int[] arr = {3, 4, 8, 8, 8, 10, 10};
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.remove(arr[i]);//出现了偶数次则删掉
            } else {
                map.put(arr[i], 1);//还未出现过则次数设为1
            }
        }
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
