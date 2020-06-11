package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 默认为非公平锁，  可以实现公平锁
 * 公平锁
 *
 *
 * 非公平锁
 *   synchronized  也是非公平锁  可以实现可重入锁
 *   上来就尝试占有锁 ，如果失败则采用类似公平锁方式  吞吐量回避公平锁大
 *可重入锁  又名递归锁  作用尽可能的避免出现死锁
 *   线程可以进入任何一个 它已经拥有的锁 所同步着的代码块
 * 自旋锁 (spinlock)  尝试获取锁 ，不会立即阻塞；采用循环方式尝试获取锁，
 *     好处减少线程的上下文切换的消耗， 缺点是循环消耗cpu
 * 独占锁（写锁）/共享锁（读锁）/互斥锁 readWriteLock
 * 读写锁 ReentrantReadWriteLock
 *
 * @author ：wangsg
 * @date ： 2020/6/11 20:41
 *  通过cas 完成自旋操作
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread> atomicReference = new AtomicReference<>( );
    public  void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "come");
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }
    public void myUnLock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+ "invoke myUnlock");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() ->{
            spinLockDemo.myLock();
             try {
                 TimeUnit.SECONDS.sleep(5);
              }
              catch (Exception e){
                 e.printStackTrace();
             }finally {
                 spinLockDemo.myUnLock();
             }
        },"t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(() ->{
            spinLockDemo.myLock();
            spinLockDemo.myUnLock();
        },"t2").start();
    }

}
