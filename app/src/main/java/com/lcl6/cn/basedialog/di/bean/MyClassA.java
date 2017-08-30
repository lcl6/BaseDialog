package com.lcl6.cn.basedialog.di.bean;

import android.util.Log;

import com.lcl6.cn.basedialog.constant.Constant;

/**
 * Created by liancl on 2017/8/30.
 */

public class MyClassA {
    private int a;
    private int b;

    public MyClassA(int a, int b) {
        this.a = a;
        this.b = b;
        Log.d(Constant.TAG, "MyClassA constructor called");
    }
    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }
}
