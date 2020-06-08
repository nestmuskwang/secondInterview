package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @author ：wangsg
 * @date ： 2020/6/7 16:25
 */
class MyData{
    volatile int number = 0 ;
    public void addT060(){
        this.number = 60 ;
    }
}

/**
 *  1.voletile可见性 2. 不保证原子性 3. 禁止指令重排
 *   缓存就是JMM的内存抽象
 *  1.1 voletile
 */
public class VolatileDemo {
    public static void main(String[] args) {
        MyData myData = new MyData();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(3);
                myData.addT060();
                System.out.println(Thread.currentThread().getName() + "\t updated number:" + myData.number);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        },"AAA").start();
        // 第二个线程就是我们的main线程
        while (myData.number == 0 ){

        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over"+ myData.number);


    }

}
