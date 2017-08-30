package com.lcl6.cn.basedialog.di.bean;

import android.util.Log;

import com.lcl6.cn.basedialog.constant.Constant;

/**
 * Created by liancl on 2017/8/30.
 */

public class MyClassB {

    private MyClassA classA;
    private int a;

    public MyClassB(MyClassA classA, int a) {
        this.classA = classA;
        this.a = a;
        Log.d(Constant.TAG, "MyClassB constructor called");
    }
    public MyClassA getClassA() {
        return classA;
    }

    public int getA() {
        return a;
    }
}
