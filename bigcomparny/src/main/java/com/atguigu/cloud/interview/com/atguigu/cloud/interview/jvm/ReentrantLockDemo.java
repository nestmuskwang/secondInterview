package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangsg
 * @date ： 2020/6/11 21:02
 *    可重入锁  同步方法调用另一个同步方法，自动获取锁
 */

class Phone implements  Runnable{

    public  synchronized  void  sendSMS(){
        System.out.println(Thread.currentThread().getName() + "invoked sendSMS()");
        sendEmail();
    }
    public  synchronized void sendEmail(){
        System.out.println(Thread.currentThread().getName()+ "#####nvoked sendEmail()");
    }
    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    public  void get()
    {
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "invoked get()");
            set();
        }finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public  void set(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getId() + "invoked set()");
        }finally {
            lock.unlock();
        }
    }
}

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()-> { try {
            phone.sendSMS();
         }
         catch (Exception e){
            e.printStackTrace();
        } },"t1").start();

        new Thread(()-> {phone.sendSMS();},"t2").start();
        Thread t3 = new Thread(phone);
        Thread t4 = new Thread(phone);
        t3.start();
        t4.start();
    }
}
