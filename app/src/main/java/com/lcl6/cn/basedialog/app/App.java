package com.lcl6.cn.basedialog.app;

import android.app.Application;

import com.lcl6.cn.utils.Utils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by liancl on 2017/7/31.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        // 初始化内存泄漏检查工具
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
