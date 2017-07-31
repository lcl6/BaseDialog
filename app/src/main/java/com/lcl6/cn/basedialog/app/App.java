package com.lcl6.cn.basedialog.app;

import android.app.Application;

import com.lcl6.cn.utils.Utils;

/**
 * Created by liancl on 2017/7/31.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
    }
}
