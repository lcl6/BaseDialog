package com.lcl6.cn.basedialog.mvp.model;

/**
 * Created by liancl on 2017/11/22.
 */

public interface VideoListModel {
    void getData(DataCallBack callBack);

    interface DataCallBack{
        void sucess();
    }

}
