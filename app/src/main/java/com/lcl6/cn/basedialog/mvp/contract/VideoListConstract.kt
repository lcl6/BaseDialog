package com.lcl6.cn.basedialog.mvp.contract

import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter
import com.lcl6.cn.component.base.mvp.view.BaseView

/**
 * Created by liancl on 2017/11/22.
 */

interface VideoListConstract {

    interface View : BaseView {
        fun showContent(list: List<VideoInfo>)
    }

    interface Present : BasePresenter<View> {
        fun getData()


    }
}
