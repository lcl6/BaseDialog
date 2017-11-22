package com.lcl6.cn.basedialog.mvp.contract;

import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

import java.util.List;

/**
 * Created by liancl on 2017/11/22.
 */

public interface VideoListConstract {

    interface View extends BaseView{
        void showContent(List<VideoInfo>list);
    }

    interface Present extends BasePresenter<View>{
        void getData();


    }
}
