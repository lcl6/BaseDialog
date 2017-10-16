package com.lcl6.cn.basedialog.di.model;

import com.lcl6.cn.basedialog.di.bean.Son;
import com.lcl6.cn.basedialog.di.scop.SonScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/10/16.
 */
@Module
public class SonMoudule {

    @Provides
    @SonScope
    Son provideSon(){
    return new Son();
    }

}
