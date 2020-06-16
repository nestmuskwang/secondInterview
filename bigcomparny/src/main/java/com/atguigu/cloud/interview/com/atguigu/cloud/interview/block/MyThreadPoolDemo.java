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
 *
 * 最大线程数  cpu密集型  cup内核数+ 1
 *
 * io密集型  1.1  cpu * 2
 *           1.2 cup数量/1 - 阻塞系数     阻塞系数 0.8--0.9 之间
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //一池4个固定线程
        int  maxvalue = (int) (Runtime.getRuntime().availableProcessors()/(1-0.9));
        System.out.println(maxvalue);
        ExecutorService threadPoll = new ThreadPoolExecutor(2,
                 maxvalue,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        try {
            for (int i = 1; i <=100; i++) {
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
