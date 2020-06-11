package com.atguigu.cloud.interview.com.atguigu.cloud.interview.enums;

import lombok.Getter;

/**
 * @author ：wangsg
 * @date ： 2020/6/12 6:18
 */
public enum CountryEnum {
    ONE(1,"齐国"),TWO(2,"楚国"),THTEE(3,"魏国"),
    FOUR(4,"韩国"),FIVE(5,"赵国"),SIEX(6,"齐国");
    @Getter private Integer retCode;
    @Getter private String retMessage;
    CountryEnum(Integer retCode ,String retMessage){
        this.retCode = retCode;
        this.retMessage = retMessage;
    }
    public  static CountryEnum forEachEunm(int integer){
        CountryEnum[] countryEnums =CountryEnum.values();
        for (CountryEnum element : countryEnums) {
            if(integer == element.getRetCode()){
                return  element;
            }
        }
        return null;
    }

}
