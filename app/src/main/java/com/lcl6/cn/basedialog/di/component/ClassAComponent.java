package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.DaggerActivity;
import com.lcl6.cn.basedialog.di.model.ModuleA;

import dagger.Component;

/**
 * Created by liancl on 2017/8/29.
 */
@Component(modules = ModuleA.class)
public interface ClassAComponent {
    void inject(DaggerActivity activity);
}