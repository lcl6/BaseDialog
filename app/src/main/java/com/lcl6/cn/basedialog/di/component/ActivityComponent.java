package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.di.model.ActivityModule;
import com.lcl6.cn.basedialog.di.scop.PerActivity;
import com.lcl6.cn.basedialog.mvp.ui.DaggerScopActivity;

import dagger.Component;

/**
 * Created by liancl on 2017/8/30.
 */
@PerActivity
@Component(dependencies = AppComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(DaggerScopActivity mainActivity);

//    void inject(ManagerActivity mainActivity);
}
