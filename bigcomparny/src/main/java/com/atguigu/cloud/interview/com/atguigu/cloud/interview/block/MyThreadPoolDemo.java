package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import com.sun.media.sound.SoftTuning;
import sun.nio.ch.ThreadPool;

import java.util.concurrent.*;

/**
 * @author ：wangsg
 * @date ： 2020/6/15 8:15
 *
 * x 线程池是通过  Executor 框架实现的 ThreadPoolExecu tor ThreadPollExecutor ThreadPollExectutor
 * Executors 有五种方法创建线程池   new  Fixed ;single ; cached
 * array arrays
 * maximumPoolsize keeplivetime  timeunit  threadFactory
 * blockingQueue <runnable> workQueue corePoolSize
 *  TreadPoolExecutor   rejectedExeutionHandler
 *  拒绝策略
 *  abortPolicy Discard callerRuns  disCardOldest
 * collection collections 工具类
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //一池4个固定线程
        ExecutorService threadPoll = new ThreadPoolExecutor(2, 5, 2L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 1; i <=10; i++) {
                threadPoll.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"\t");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoll.shutdown();
        }
    }

    public static void poolMothed(){
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();
        //ExecutorService threadPool = Executors.newCachedThreadPool();
        //每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <=10; i++) {
                threadPool.execute(() ->{
                    System.out.println(Thread.currentThread().getName() + "办理业务");
                });
                TimeUnit.SECONDS.sleep(1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
