package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import cn.hutool.core.collection.ConcurrentHashSet;

import javax.sound.midi.Soundbank;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author ：wangsg
 * @date ： 2020/6/10 23:09
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //Set<String> set = new HashSet<>();
        Map<String,String> map = new ConcurrentHashMap<>( );
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),"ss");
                System.out.println(map);
            },String.valueOf(i)).start();
        }
        /**
         * CopyOnWriteArraySet CopyOnWriteArraylist()
         */
    }
    public static void  ListNotSafe(){
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
        /**
         * CopyOnWriteArraySet CopyOnWriteArraylist()
         */

        //List<String> list1 = Arrays.asList("a","b","c","d");
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /**     CopyOnWrteArrayList<>()   add 并发修改线程安全原理
         *  public boolean add(E e) {
         *         final ReentrantLock lock = this.lock;
         *         lock.lock();
         *         try {
         *             Object[] elements = getArray();
         *             int len = elements.length;
         *             Object[] newElements = Arrays.copyOf(elements, len + 1);
         *             newElements[len] = e;
         *             setArray(newElements);
         *             return true;
         *         } finally {
         *             lock.unlock();
         *         }
         *     }
         *
         * 写时复制 读写分离
         *  底层与原理
         * 1. 故障现象
         *    java.util.ConcurrentModificationException
         * 2.导致原因
         *    并非修改争抢导致， 一个线程正在写， 另外一个线程进来抢夺，导致并发修改异常
         * 3. 解决方案
         *      3.1  new Vector<>()
         *      3.2 Collections.synchronizedList(new ArrayList<>()）
         *      3.3 new CopyOnWrteArrayList<>()  add方法为使用ReentreLock
         * 4. 优化建议
         *
         */
    }
}
