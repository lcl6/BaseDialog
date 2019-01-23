package com.lcl6.cn.basedialog.mvp.contract

import com.lcl.greendao.bean.DanmakuInfo
import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter
import com.lcl6.cn.component.base.mvp.view.BaseView

import java.io.InputStream

/**
 * Created by liancl on 2017/11/28.
 */

interface DanmukuContract {
    interface View : BaseView {

        /**
         * 获取Video数据
         * @param data 数据
         */
        fun loadData(data: VideoInfo)

        /**
         * 获取弹幕数据
         * @param inputStream 数据
         */
        fun loadDanmakuData(inputStream: InputStream)
    }

    interface Present : BasePresenter<View> {
        /**
         * 添加一条弹幕到数据库
         * @param danmakuInfo
         */
        fun addDanmaku(danmakuInfo: DanmakuInfo)

        /**
         * 清空该视频所有缓存弹幕
         */
        fun cleanDanmaku()
    }

}
