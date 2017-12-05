package com.lcl6.cn.basedialog.mvp.model.impl;

import com.lcl6.cn.basedialog.api.Api;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.base.manager.observel.RxObservable;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.mvp.model.VideoListModel;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;

import static com.lcl6.cn.basedialog.api.Api.VIDEO_DOMAIN_NAME;

/**
 *
 * Created by liancl on 2017/11/22.
 */

public class VideoListModelImpl implements VideoListModel {


    @Override
    public void getData(final DataCallBack callBack) {
        HttpUrl httpUrl =   App.getAppComponent().getRetrofitManager().fetchDomain(VIDEO_DOMAIN_NAME);
        if (httpUrl == null || !httpUrl.toString().equals(Api.NEWS_HOST)) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            App.getAppComponent().getRetrofitManager().putDomain(VIDEO_DOMAIN_NAME, Api.NEWS_HOST);
        }

        App.getAppComponent()
                .getNetWorkManager()
                .getRetrofit()
                .create(Api.class)
                .getVideoList("V9LG4B3A0",10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(flatMapVideo("V9LG4B3A0"))
                .subscribe(new RxObservable<List<VideoInfo>>() {
                    @Override
                    protected void onRxComplete() {
                    }

                    @Override
                    protected void onRxNext(List<VideoInfo> list) {
                        callBack.sucess(list);
                    }

                    @Override
                    protected void onRxSubscribe(Disposable d) {
                    }

                    @Override
                    protected void onRxError(Throwable e) {
                        callBack.error(e);
                    }
                });
    }


    /**
     * 类型转换
     * @param typeStr 视频类型
     */
    private static Function<Map<String, List<VideoInfo>>, Observable<List<VideoInfo>>> flatMapVideo(final String typeStr) {
        return new Function<Map<String, List<VideoInfo>>, Observable<List<VideoInfo>>>() {
            @Override
            public Observable<List<VideoInfo>> apply(Map<String, List<VideoInfo>> stringListMap) throws Exception {
                return Observable.just(stringListMap.get(typeStr));
            }
        };
    }
}
