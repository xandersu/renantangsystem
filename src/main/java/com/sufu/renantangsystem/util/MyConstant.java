package com.sufu.renantangsystem.util;

/**
 * Created by lenovo on 2018/6/19.
 */
public class MyConstant {

    public static String getSex(int i) {
        String[] sexArr = {"女", "男"};
        if (i == 0 || i == 1) {
            return sexArr[i];
        }
        return "";
    }
}
