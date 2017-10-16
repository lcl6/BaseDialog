package com.lcl6.cn.basedialog.di.bean;

import android.util.Log;

import com.lcl6.cn.basedialog.constant.Constant;

import javax.inject.Inject;

/**
 * Created by liancl on 2017/10/16.
 */

public class Car {

    @Inject
    public Car() {
    }

    public void go(){
        Log.e(Constant.TAG, "go: ");
    }

}
