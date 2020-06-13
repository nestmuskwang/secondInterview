package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangsg
 * @date ： 2020/6/13 7:39
 *  题目 手写一个初始值为0 的变量 两个线程交替操作
 *  线程  操作  资源类
 *  判断， 干活， 唤醒/通知
 *  严防多线程下的虚假唤醒
 */
class  ShareData{
    private  int i =0;
    private Lock  lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment(){
        lock.lock();
        try {
            while (i != 0 ){
                condition.await();
            }
            i ++;
            System.out.println(Thread.currentThread().getName()+ i);
            //通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void decrement(){
        lock.lock();
        try {
            while (i == 0){
                condition.await();
            }
            i --;
            System.out.println(Thread.currentThread().getName()+ i);
            //通知唤醒
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}


public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(() ->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(() ->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(() ->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"cc").start();

        new Thread(() ->{
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"dd").start();
    }
}
