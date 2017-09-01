package com.lcl6.cn.basedialog.di.model;

import com.lcl6.cn.basedialog.base.manager.NetWorkManager;
import com.lcl6.cn.basedialog.di.bean.MyClassA;

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

    public AppModule(int a, int b) {
        this.a = a;
        this.b = b;
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
    MyClassA provideMyClassA(@Named("a")int a ,@Named("b")int b){
        return new MyClassA(a,b);
    }

    @Singleton
    @Provides
    NetWorkManager provideNetWorkManager(){
        return new NetWorkManager();
    }




}
