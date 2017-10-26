package com.lcl6.cn.basedialog.app;

import android.app.Application;

import com.lcl6.cn.basedialog.di.component.AppComponent;
import com.lcl6.cn.basedialog.di.component.DaggerAppComponent;
import com.lcl6.cn.basedialog.di.model.AppModule;
import com.lcl6.cn.utils.Utils;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by liancl on 2017/7/31.
 */

public class App extends Application {

//    private static App instance;
    private static AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(getApplicationContext());
        // 初始化内存泄漏检查工具
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(2, 3,this)).build();
        appComponent.inject(this);

//        AppCrashUtil.getInstance(Constant.PATH).init(this, new AppCrashUtil.CrashUploader() {
//            @Override
//            public void uploadCrashMessage(ConcurrentHashMap<String, Object> infos) {
//                Log.e(Constant.TAG, "uploadCrashMessage: " );
//            }
//        });


    }


//    public static App getInstance(){
//        return instance;
//    }

    public static AppComponent getAppComponent(){
        return appComponent;
    }

}
