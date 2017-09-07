package com.lcl6.cn.basedialog.di.component;

import android.app.Activity;

import com.lcl6.cn.basedialog.ui.activity.DaggerActivity;
import com.lcl6.cn.basedialog.di.model.ModuleA;
import com.lcl6.cn.basedialog.di.scop.PerActivity;

import dagger.Component;

/**
 * Created by liancl on 2017/8/29.
 */

//@Singleton
@PerActivity
@Component(dependencies = AppComponent.class,modules = ModuleA.class)
public interface ClassAComponent {
    Activity getActivity();

    void inject(DaggerActivity activity);
//    void inject(App app);
}
