package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import cn.hutool.core.collection.ConcurrentHashSet;

import java.sql.SQLOutput;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author ：wangsg
 * @date ： 2020/6/12 22:10
 * 1. 队列
 *
 * 2. 阻塞队列
 *   有好的一面
 *   不得不阻塞 ，如何管理
 */
public class BlockKingQueueDemo {
    public static void main(String[] args)  throws  Exception{
        //BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(()-> {
            try {
                System.out.println(Thread.currentThread().getName()+ "put a");
                blockingQueue.put("a");
                System.out.println(Thread.currentThread().getName()+ "put b");
                blockingQueue.put("b");
                System.out.println(Thread.currentThread().getName()+ "put v");
                blockingQueue.put("v");
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        },"线程a").start();

        new Thread(()-> {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+ "take a");
                blockingQueue.take();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+ "take b");
                blockingQueue.take();
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName()+ "take v");
                blockingQueue.take();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        },"线程b").start();



    }
}
