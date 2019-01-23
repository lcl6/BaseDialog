package com.lcl6.cn.basedialog.mvp.presenter


import com.alibaba.fastjson.JSON
import com.lcl.greendao.bean.DanmakuInfo
import com.lcl.greendao.dao.DanmakuInfoDao
import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.basedialog.mvp.contract.DanmukuContract
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayInputStream

/**
 * Created by liancl on 2017/11/28.
 */

class VideoPlayerPresenter(private val danmakuInfoDao: DanmakuInfoDao, private val mVideoData: VideoInfo) : RxPresenter<DanmukuContract.View>(), DanmukuContract.Present {

    override fun addDanmaku(danmakuInfo: DanmakuInfo) {
        //将弹幕保存
        danmakuInfoDao.insert(danmakuInfo)

        //将弹幕转为流
        val list = danmakuInfoDao.queryBuilder().where(DanmakuInfoDao.Properties.Vid.eq(mVideoData.vid)).list()
        Observable.just(list)
                .map { danmakuInfos ->
                    val jsonStr = JSON.toJSONString(danmakuInfos)
                    ByteArrayInputStream(jsonStr.toByteArray())
                }.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { inputStream -> mView.loadDanmakuData(inputStream) }


    }

    override fun cleanDanmaku() {

    }
}
