package com.guigu.basis.JUC.countdownlatch;

import lombok.Getter;

/**
 * @program： com.guigu.basis.JUC
 * @description:
 * @author:
 * @create: 2021-01-13 16:10
 **/
public enum CountryEnum {
    ONE(1, "齐"),
    TWE(2, "楚"),
    THREE(3, "燕"),
    FOUR(4, "韩"),
    FIVE(5, "赵"),
    SIX(6, "魏");

    @Getter
    private Integer retCode;
    @Getter
    private String retMessage;

    CountryEnum(int retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum forEach_CountryEnum(int index) {
        CountryEnum[] myArray = CountryEnum.values();
        for (CountryEnum countryEnum : myArray) {
            if (index == countryEnum.retCode) {
                return countryEnum;
            }
        }
        return null;
    }
}
