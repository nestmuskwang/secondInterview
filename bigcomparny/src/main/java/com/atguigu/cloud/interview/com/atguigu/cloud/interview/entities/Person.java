package com.atguigu.cloud.interview.com.atguigu.cloud.interview.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ：wangsg
 * @date ： 2020/6/11 20:05
 */
@NoArgsConstructor
@Getter
@Setter
public class Person {
    private Integer id;
    private String personName;

    public Person(String personName){
        this.personName = personName;
    }
}
