package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.base.manager.NetWorkManager;
import com.lcl6.cn.basedialog.di.bean.MyClassA;
import com.lcl6.cn.basedialog.di.model.AppModule;
import com.lcl6.cn.component.net.RetrofitManager;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by liancl on 2017/8/30.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(App app);
    MyClassA getClassA();
    NetWorkManager getNetWorkManager();
    App getContext();  // 提供App的Context
    RetrofitManager getRetrofitManager();

}
