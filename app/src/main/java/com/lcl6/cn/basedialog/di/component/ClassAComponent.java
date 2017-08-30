package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.DaggerActivity;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.di.model.ModuleA;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by liancl on 2017/8/29.
 */

@Singleton
@Component(modules = ModuleA.class)
public interface ClassAComponent {
    void inject(DaggerActivity activity);
    void inject(App app);
}
