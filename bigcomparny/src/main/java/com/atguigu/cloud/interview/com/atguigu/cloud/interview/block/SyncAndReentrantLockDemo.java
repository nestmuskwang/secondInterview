package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：wangsg
 * @date ： 2020/6/13 8:15
 * synchroized 和lock 的区别是什么
 *  synchronized 是jvm关键字层面
 *    底层通过monitor 对象完成
 *  lock 是具体的类  是java api层面
 *
 *  2. 使用方法上 syn 不需要手动释放锁
 *
 *  3. 等待是否可中断  syn 不可
 *        lock 可中断  .interruptible
 *
 *  4.是否公平
 *
 *  5.lock 可以绑定多个condition   可以精确唤醒   调用的维度和精度加强了
 *
 */

class  ShareSource{
    private  int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public  void print5(){
        lock.lock();
        try {
            while (number !=1){
                c1.await();
            }
            for (int i = 1; i <=5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number =2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void print10(){
        lock.lock();
        try {
            while (number !=2){
                c2.await();
            }
            for (int i = 1; i <=10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number =3;
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public  void print15(){
        lock.lock();
        try {
            while (number !=3){
                c3.await();
            }
            for (int i = 1; i <=15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number =1;
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareSource shareSource = new ShareSource();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareSource.print5();
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareSource.print10();
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1; i <= 5; i++) {
                shareSource.print15();
            }
        },"CC").start();
    }
}
