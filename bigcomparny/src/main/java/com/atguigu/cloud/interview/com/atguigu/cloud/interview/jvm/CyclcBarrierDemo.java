package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.concurrent.CyclicBarrier;

/**
 * @author ：wangsg
 * @date ： 2020/6/12 6:43
 */
public class CyclcBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");});

        for (int i = 1; i <= 7; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println("收集地"+tempInt+"颗龙珠");
                 try {
                     cyclicBarrier.await();
                  }
                  catch (Exception e){
                     e.printStackTrace();
                 }

            },String.valueOf(i)).start();
        }
    }

}
