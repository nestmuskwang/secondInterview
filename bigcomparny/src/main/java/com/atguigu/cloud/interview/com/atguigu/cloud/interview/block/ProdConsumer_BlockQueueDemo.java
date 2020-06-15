package com.atguigu.cloud.interview.com.atguigu.cloud.interview.block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：wangsg
 * @date ： 2020/6/13 15:38
 */
class  MySource{
    private  volatile AtomicInteger  number = new AtomicInteger(0);
    private  boolean flag = true;
    private BlockingQueue<String> blockingQueue = null;

    public   MySource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public  void myProd(){
         String data = null;
         boolean result ;
        try {
            while (flag){
                data = number.incrementAndGet()+"";
                System.out.println(data);
                result = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
                if(result){
                    System.out.println(Thread.currentThread().getName() + "生产插入队列成功" + "\t"+ data);
                }else {
                    System.out.println(Thread.currentThread().getName() + "生产插入队列失败" + "\t"+ data);

                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() +"生产结束了");
    }
    public  void myConsumer(){
        String result = null;
        try {
            while (flag){
                result = blockingQueue.poll(2L, TimeUnit.SECONDS);
                if(null == result || result.equalsIgnoreCase("")){
                    flag = false;
                    System.out.println(Thread.currentThread().getName() + " 超过2秒没有取得蛋糕，消费退出");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "消费成功"+ "\t"+ result);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public  void stop(){
        this.flag = false;
    }
}
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MySource mySource =new MySource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            mySource.myProd();
        },"pord").start();
        new Thread(()->{
            mySource.myConsumer();
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("工厂生产消费5秒钟");
        System.out.println();
        System.out.println();
        System.out.println();

        new Thread(()->{mySource.stop();},"stop").start();
    }


}
