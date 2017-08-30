package com.lcl6.cn.basedialog.di.model;

import com.lcl6.cn.basedialog.di.bean.ClassA;
import com.lcl6.cn.basedialog.di.bean.ClassB;

import javax.inject.Named;
import javax.inject.Singleton;

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

    /**第二种写法*/
    @Provides
    ClassA provideClassA(@Named("a")int a,@Named("b")int b){
        return new ClassA(a,b);
    }

    @Singleton
    @Provides
    ClassB provideClassB(ClassA classA, @Named("b")int b){
        return new ClassB(classA,b);
    }

}
