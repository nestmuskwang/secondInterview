package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import javax.sound.midi.Soundbank;

/**
 * @author ：wangsg
 * @date ： 2020/6/8 21:27
 */
public class SingletonDemo {
    //双端检索机制不一定线程安全， 因为存在指令重排
    private  static volatile SingletonDemo singletonDemo = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName());
    }
    public static  SingletonDemo getSingletonDemo(){
        if(singletonDemo == null){
            synchronized(SingletonDemo.class){
                if(singletonDemo ==null){
                    singletonDemo = new SingletonDemo();
                }
            }

        }
        return singletonDemo;
    }

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(() -> {
                SingletonDemo.getSingletonDemo();
            },String.valueOf(i)).start();
        }
    }
}
