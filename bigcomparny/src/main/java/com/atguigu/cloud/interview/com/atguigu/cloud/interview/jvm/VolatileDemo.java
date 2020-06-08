package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：wangsg
 * @date ： 2020/6/7 16:25
 */
class MyData{
    volatile int number = 0 ;
    public void addT060(){
        this.number = 60 ;
    }
    //此时 number 是加了volatile的
    public  void  addPlusPlus(){
        number ++ ;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public  void  addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/**
 *  1.voletile可见性 2. 不保证原子性 3. 禁止指令重排
 *   缓存就是JMM的内存抽象
 *  1.1 voletile
 *
 *  2. 1验证volatile 不保证原子性(最终一致性能不能保证)
 *   不可分割，完整性 ，要么同时成功，要么同时失败
 *     2.2
 *     2.4 如何解决原子性
 *      加sycronized
 *      atomic
 *
 */
public class VolatileDemo {
    public static void main(String[] args)   {
        MyData myData = new MyData ();
        for (int i = 0; i < 30 ; i++) {
            new Thread(() -> {
                for (int j = 0; j <1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addAtomic();
                }
            },String.valueOf(i)).start();
        }
        //需要等20个线程完成后，main 取得最终结果值
       while (Thread.activeCount()> 2){ //当前活跃线程数量
           Thread.yield();
       }
       System.out.println(Thread.currentThread().getName() + "\t finally number" + myData.number);
       System.out.println(Thread.currentThread().getName() + "\t finally number" + myData.atomicInteger);
    }
    public static  void seeOk(){
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
