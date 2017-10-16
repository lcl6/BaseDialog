package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.di.bean.Son;
import com.lcl6.cn.basedialog.di.model.SonMoudule;
import com.lcl6.cn.basedialog.di.scop.SonScope;

import dagger.Subcomponent;

/**
 * Created by liancl on 2017/10/16.
 */
@SonScope
@Subcomponent(modules = SonMoudule.class)
public interface SonComponent {
    void inject(Son son);

    @Subcomponent.Builder
    interface Builder{
        SonComponent build();
    }
}
