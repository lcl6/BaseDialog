package com.lcl6.cn.basedialog.di.model;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/8/29.
 */
@Module
public class ModuleA {
    private int a;
    private int b;

    public ModuleA(int a, int b) {
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

}
