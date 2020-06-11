package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import com.atguigu.cloud.interview.com.atguigu.cloud.interview.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * @author ：wangsg
 * @date ： 2020/6/12 6:03
 *  before  after   修改前后的差异性
 *
 *
 *
 *
 */

public class CountDownLatchDeom {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " 被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEachEunm(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() +"大秦一统天下");
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.TWO);
        System.out.println(CountryEnum.THTEE);
    }

    public static void  closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "下班了");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() +"保安关闭大厦");
    }
}
























