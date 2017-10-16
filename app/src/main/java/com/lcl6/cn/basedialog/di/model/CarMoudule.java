package com.lcl6.cn.basedialog.di.model;

import com.lcl6.cn.basedialog.di.bean.BigCar;
import com.lcl6.cn.basedialog.di.component.SonComponent;
import com.lcl6.cn.basedialog.di.scop.ManScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/10/16.
 */

@Module(subcomponents = SonComponent.class)
public class CarMoudule {

    @ManScope
    @Provides
    BigCar provideCar(){
        return new BigCar();
    }

}
