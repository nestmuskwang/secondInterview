package com.atguigu.cloud.interview.com.atguigu.cloud.interview.jvm;


import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


import java.util.concurrent.atomic.AtomicReference;

/**
 * @author ：wangsg
 * @date ： 2020/6/10 6:03
 *  原子引用
 *  解决ABA   理解原子引用 + 新增一种机制 ，那就是修改版本号（类似时间戳）
 */
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User z3 = new User("z3", 22);
        User li4 = new User("li4", 25);
        AtomicReference<User> userAtomicReference = new AtomicReference<User>();
        userAtomicReference.set(z3);
        System.out.println(userAtomicReference.compareAndSet(z3, li4) + "\t"+ userAtomicReference.get().toString());
        System.out.println(userAtomicReference.compareAndSet(z3, li4) + "\t"+ userAtomicReference.get().toString());

    }
}

@Getter
@ToString
@AllArgsConstructor
class  User{
    String userName;
    int age ;
}