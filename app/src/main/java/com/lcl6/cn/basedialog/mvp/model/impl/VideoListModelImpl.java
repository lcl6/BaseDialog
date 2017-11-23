package com.lcl6.cn.basedialog.mvp.model.impl;

import com.lcl6.cn.basedialog.api.Api;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.mvp.model.VideoListModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 *
 * Created by liancl on 2017/11/22.
 */

public class VideoListModelImpl implements VideoListModel {


    @Inject
    public VideoListModelImpl() {
    }

    @Override
    public void getData(DataCallBack callBack) {
       App.getAppComponent().getNetWorkManager().getRetrofit().create(Api.class).getVideoList("VAG4JHJUR",10)
               .subscribeOn(Schedulers.io())
               .unsubscribeOn(Schedulers.io())
               .subscribeOn(AndroidSchedulers.mainThread())
               .observeOn(AndroidSchedulers.mainThread())
               .flatMap(flatMapVideo("V9LG4B3A0"));
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
