package com.lcl6.cn.basedialog.mvp.model;

import com.lcl6.cn.basedialog.bean.JsoupBean;

import java.util.List;

/**
 * Created by liancl on 2017/8/23.
 */

public interface NewsModel {

    void getData(LoadCompleteListener listener);

    interface  LoadCompleteListener{
        void comlpete(List<JsoupBean> list);
    }


}
