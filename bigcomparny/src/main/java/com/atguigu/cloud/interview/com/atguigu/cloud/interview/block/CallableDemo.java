package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author ：wangsg
 * @date ： 2020/6/15 5:58
 * 注入方法的 构造注入
 */
class  MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() +"线程进入");
        TimeUnit.SECONDS.sleep(3);

        System.out.println(Thread.currentThread().getName() +"线程退出");
        return 1024;
    }
}

/**
 *  适配器模式找到共同实现runable 与callable接口的类
 *  futureTask
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        FutureTask<Integer> futureTask2 = new FutureTask<>(new MyThread());

        new Thread(futureTask,"AA").start();
        new Thread(futureTask2,"BB").start();
        /*Integer integer ;
        while (!futureTask.isDone()){
            integer = futureTask.get();
        }*/
        int miannum=100;
        System.out.println("main+++++++++++++++++");
        Integer integer = futureTask.get(); //返回值要放到最后

        System.out.println(Thread.currentThread().getName() + "result"+
                "\t"+(integer+miannum));

    }
}
