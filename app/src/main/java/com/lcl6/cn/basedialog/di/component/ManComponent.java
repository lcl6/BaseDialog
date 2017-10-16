package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.di.bean.Man;
import com.lcl6.cn.basedialog.di.model.CarMoudule;
import com.lcl6.cn.basedialog.di.scop.ManScope;

import dagger.Component;

/**
 * Created by liancl on 2017/10/16.
 */

@ManScope
@Component(modules = CarMoudule.class)
public interface ManComponent {
    void inject(Man man);

    SonComponent.Builder sonComponent();
}
