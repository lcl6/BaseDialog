package com.lcl6.cn.basedialog.di.model;

import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.base.manager.NetWorkManager;
import com.lcl6.cn.basedialog.di.bean.MyClassA;
import com.lcl6.cn.component.net.RetrofitManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/8/30.
 */

@Module
public class AppModule {
    private int a;
    private int b;
    private App app;

    public AppModule(int a, int b,App app) {
        this.a = a;
        this.b = b;
        this.app = app;
    }
    @Provides
    @Named("a")
    int provideIntA(){
        return a;
    }
    @Provides
    @Named("b")
    int provideIntB(){
        return b;
    }

    @Singleton
    @Provides
    MyClassA provideMyClassA(@Named("a")int a , @Named("b")int b){
        return new MyClassA(a,b);
    }

    @Singleton
    @Provides
    NetWorkManager provideNetWorkManager(){
        return new NetWorkManager();
    }

    @Singleton
    @Provides
    App provideApp(){
        return app;
    }
    @Singleton
    @Provides
    RetrofitManager provideRetrofitManager(){
        return new RetrofitManager();
    }





}
