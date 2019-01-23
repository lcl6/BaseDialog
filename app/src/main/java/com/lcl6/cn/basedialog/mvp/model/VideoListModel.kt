package com.lcl6.cn.basedialog.mvp.model

import com.lcl6.cn.basedialog.bean.VideoInfo

/**
 * Created by liancl on 2017/11/22.
 */

interface VideoListModel {
    fun getData(callBack: DataCallBack)

    interface DataCallBack {
        fun sucess(list: List<VideoInfo>)
        fun error(e: Throwable)
    }

}
