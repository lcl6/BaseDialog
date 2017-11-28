package com.lcl6.cn.basedialog.di.component;

import com.lcl6.cn.basedialog.di.model.VideoModule;
import com.lcl6.cn.basedialog.mvp.ui.VideoActivity;
import com.lcl6.cn.basedialog.mvp.ui.VideoPlayActivity;

import dagger.Component;

/**
 * Created by liancl on 2017/11/22.
 */

@Component(modules = VideoModule.class)
public interface VideoComponent {
   void  inject(VideoActivity activity);
   void  inject(VideoPlayActivity activity);

}
