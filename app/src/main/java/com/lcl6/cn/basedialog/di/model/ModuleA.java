package com.lcl6.cn.basedialog.di.model;

import android.app.Activity;

import com.lcl6.cn.basedialog.di.bean.ClassA;
import com.lcl6.cn.basedialog.di.bean.ClassB;
import com.lcl6.cn.basedialog.di.scop.PerActivity;

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
    private Activity activity;

    public ModuleA(int a, int b, Activity activity) {
        this.a = a;
        this.b = b;
        this.activity = activity;
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

    @PerActivity
    @Provides
    ClassB provideClassB(ClassA classA, @Named("b")int b){
        return new ClassB(classA,b);
    }

    @Provides
    Activity provideActivity(){
        return activity;
    }

}
