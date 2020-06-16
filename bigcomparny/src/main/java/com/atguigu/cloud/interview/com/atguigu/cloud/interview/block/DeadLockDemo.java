package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import java.util.concurrent.TimeUnit;

/**
 * @author ：wangsg
 * @date ： 2020/6/15 23:01
 * 两个或两个以上线程争抢资源造成的互相持有锁的zhuangt
  若无外力干涉无法推进下去
 */
class  HoldLockThread  implements Runnable{
    private  String lockA;
    private  String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName() + "\t"+lockA);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+ "\t"+lockB);
            }
        }

    }
}

public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        new  Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
        new  Thread(new HoldLockThread(lockB,lockA),"ThreadBB").start();
    }
}
