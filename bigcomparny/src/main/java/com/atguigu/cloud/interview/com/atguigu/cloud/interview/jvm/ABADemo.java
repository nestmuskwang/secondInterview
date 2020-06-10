package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import org.aspectj.weaver.ast.Var;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author ：wangsg
 * @date ： 2020/6/10 6:31
 *  AtomicStampedReference<V>
 */
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static  AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100,1);
    public static void main(String[] args) {

        new Thread(() -> {
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(() ->{
             try {
                 TimeUnit.SECONDS.sleep(1);
              }
              catch (InterruptedException e){
                 e.printStackTrace();
             }
            System.out.println(Thread.currentThread().getName()+atomicReference.compareAndSet(100, 2019) + "\t" + atomicReference.get());
        },"t2").start();


        System.out.println("+================= 解决ABA问题");


        new Thread(() ->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() + "第一次版本号" + stamp);
            try {
                 TimeUnit.SECONDS.sleep(1);
              }
              catch (InterruptedException e){
                 e.printStackTrace();
             }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "第2次版本号" + stamp);
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName() + "第3次版本号" + stamp);

        },"t3").start();

        new Thread(() ->{
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() + "第1次版本号" + stamp);
                try {
                     TimeUnit.SECONDS.sleep(5);
                  }
                  catch (InterruptedException e){
                     e.printStackTrace();
                 }
            boolean b = atomicStampedReference.compareAndSet(100, 2020, stamp, stamp + 1);
            System.out.println( Thread.currentThread().getName()+ "result"+ atomicStampedReference.getStamp()+ atomicStampedReference.getReference());
            System.out.println(b);
        },"t4").start();

    }


}
