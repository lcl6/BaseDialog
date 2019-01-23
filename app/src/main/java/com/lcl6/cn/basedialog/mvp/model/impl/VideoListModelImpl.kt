package com.lcl6.cn.basedialog.mvp.model.impl

import com.lcl6.cn.basedialog.api.Api
import com.lcl6.cn.basedialog.api.Api.VIDEO_DOMAIN_NAME
import com.lcl6.cn.basedialog.app.App
import com.lcl6.cn.basedialog.base.manager.observel.RxObservable
import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.basedialog.mvp.model.VideoListModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

/**
 *
 * Created by liancl on 2017/11/22.
 */

class VideoListModelImpl : VideoListModel {


    override fun getData(callBack: VideoListModel.DataCallBack) {
        val httpUrl = App.getAppComponent().retrofitManager.fetchDomain(VIDEO_DOMAIN_NAME)
        if (httpUrl == null || httpUrl.toString() != Api.NEWS_HOST) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            App.getAppComponent().retrofitManager.putDomain(VIDEO_DOMAIN_NAME, Api.NEWS_HOST)
        }

        App.getAppComponent()
                .netWorkManager
                .retrofit
                .create(Api::class.java)
                .getVideoList("V9LG4B3A0", 10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(flatMapVideo("V9LG4B3A0"))
                .subscribe(object : RxObservable<List<VideoInfo>>() {
                    override fun onRxComplete() {}

                    override fun onRxNext(list: List<VideoInfo>) {
                        callBack.sucess(list)
                    }

                    override fun onRxSubscribe(d: Disposable) {}

                    override fun onRxError(e: Throwable) {
                        callBack.error(e)
                    }
                })
    }


    /**
     * 类型转换
     * @param typeStr 视频类型
     */
    private fun flatMapVideo(typeStr: String): Function<Map<String, List<VideoInfo>>, Observable<List<VideoInfo>>> {
        return Function { stringListMap -> Observable.just(stringListMap[typeStr]) }
    }
}
