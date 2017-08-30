package com.lcl6.cn.basedialog.di.model;

import com.lcl6.cn.basedialog.di.bean.MyClassA;
import com.lcl6.cn.basedialog.di.bean.MyClassB;
import com.lcl6.cn.basedialog.di.scop.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/8/30.
 */

@Module
public class ActivityModule {
    private int c;

    public ActivityModule(int c) {
        this.c = c;
    }
    @Provides
    public int privoidIntC(){
        return c;
    }
    @PerActivity
    @Provides
    public MyClassB provideMyClassB(MyClassA classA,int c){
        return new MyClassB(classA,c);
    }


}
