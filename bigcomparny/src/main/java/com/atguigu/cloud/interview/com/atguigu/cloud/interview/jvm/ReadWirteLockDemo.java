package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author ：wangsg
 * @date ： 2020/6/11 22:37
 * 多个线程同时读一个一个资源没有任何问题，
 * 但是 有一个线程去写共享资源， 就不应该在有其他线程可以对资源进行读或写
 *  读 -读 共存
 *  读写 不能共存
 *  写写 不能共存
 *
 *  ABABABBABBA   after  学习的之前和之后  before
 */

class  MyCache{
    private  volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public  void put(String key, Object value) {
         readWriteLock.writeLock().lock();
         try {
             try {
                 System.out.println(Thread.currentThread().getName() + "正在写入"+ key);
                 TimeUnit.MILLISECONDS.sleep(300);
                 map.put(key,value);
                 System.out.println(Thread.currentThread().getName() + "写入完成");
             }
             catch (InterruptedException e){
                 e.printStackTrace();
             }
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             readWriteLock.writeLock().unlock();
         }
    }

    public  void get(String key) {
        readWriteLock.readLock().lock();
        try {
            try {
                System.out.println(Thread.currentThread().getName() + "正在读取");
                TimeUnit.MILLISECONDS.sleep(300);
                Object o = map.get(key);
                System.out.println(Thread.currentThread().getName() + "读取完成"+o);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}

public class ReadWirteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            final int temp =i;
            new Thread(() ->{
                myCache.put(temp+"",temp+"");
            },i+"").start();
        }
        for (int i = 0; i < 5; i++) {
            final int temp =i;
            new Thread(() ->{
                myCache.get(temp+"");
            },i+"").start();
        }
    }
}
