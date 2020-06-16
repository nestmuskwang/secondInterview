package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import ch.qos.logback.core.pattern.FormatInfo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wangsg
 * @date ： 2020/6/12 6:54
 * 多个资源抢多个资源
 * 信号量 目的1.用于多个共享资源的互斥作用2.用于并发线程数的控制
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <=6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+ "获取车位");
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "释放车位");
                }
            },String.valueOf(i)).start();
        }
    }
}
