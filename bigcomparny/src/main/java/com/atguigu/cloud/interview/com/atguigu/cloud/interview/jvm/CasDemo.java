package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：wangsg
 * @date ： 2020/6/8 22:01
 */
public class CasDemo {
    /**cas 比较并交换 capareAndSet
     *   底层原理 1.1 自旋锁
     *           1.2 unsafe 类 sun.misc包下的   rt.jar包里边 大部分都是native方法
     *            unsafe 是CAS的核心类 ， java无法直接访问底层操作系统， 需要通过本地方法访问 ， unsafe相当于
     *            一个厚么，基于该类可以直接操作特定内存的数据
     *   2. 变量valueOffset, 标识该变量值在内存中的偏移地址
     *
     *   CAS全程 compare-and-swap 是一条cpu并发原语
     *   并发原语的执行必须是连续的 ， 在执行过程中不允许被中断， 也就是说CAS是一条原子指令
     *  为什么不用synchronized  ： 加锁会解决问题但是会降低并发性 一致性保证， 并发性下降
     *  CAS 原理 Unsafe 类 + CAS思想（自旋）
     *  CAS 缺点 1. 循环时间长，开销大
     *    2.只能保证一个共享变量的原子操作 ， 3. ABA问题 但是并不影响数据的一致性（有些业务可以忽略这个问题）
     *       解决该问题 可以引入版本号机制
     *
     *   AtomicInteger 原子更新引入 -> 如何规避ABA问题
     */

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2019) +
                "\tcurrent data" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024) +
                "\tcurrent data" + atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
