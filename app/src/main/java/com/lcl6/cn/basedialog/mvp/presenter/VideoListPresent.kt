package com.lcl6.cn.basedialog.mvp.presenter

import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.basedialog.mvp.contract.VideoListConstract
import com.lcl6.cn.basedialog.mvp.model.VideoListModel
import com.lcl6.cn.basedialog.mvp.model.impl.VideoListModelImpl
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter

/**
 * Created by liancl on 2017/11/22.
 */

class VideoListPresent : RxPresenter<VideoListConstract.View>(), VideoListConstract.Present {

    internal var videoListModel = VideoListModelImpl()

    override fun getData() {
        videoListModel.getData(object : VideoListModel.DataCallBack {
            override fun sucess(list: List<VideoInfo>) {
                if (mView != null) {
                    mView.showContent(list)
                }
            }

            override fun error(e: Throwable) {
                if (mView != null) {
                    mView.stateError()
                }
            }
        })
    }
}
