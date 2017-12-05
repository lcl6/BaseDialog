package com.lcl6.cn.basedialog.mvp.model;

import com.lcl6.cn.basedialog.bean.VideoInfo;

import java.util.List;

/**
 * Created by liancl on 2017/11/22.
 */

public interface VideoListModel {
    void getData(DataCallBack callBack);

    interface DataCallBack{
        void sucess(List<VideoInfo> list);
        void error(Throwable e);
    }

}
