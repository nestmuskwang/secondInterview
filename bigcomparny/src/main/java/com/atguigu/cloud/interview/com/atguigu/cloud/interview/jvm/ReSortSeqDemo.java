package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;

/**
 * @author ：wangsg
 * @date ： 2020/6/8 21:17
 */
public class ReSortSeqDemo {
    int a = 0 ;
    boolean flag = false;
// 内存屏障另一种 将工作内存中的共享变量刷新回到主内存
    public  void method01(){
        a = 1;
        flag = true;
    }
    public void  method02(){
        if(flag){
            a = a + 5;
            System.out.println(a);
        }
    }
}
