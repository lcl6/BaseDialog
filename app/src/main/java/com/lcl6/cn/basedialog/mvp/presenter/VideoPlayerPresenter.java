package com.lcl6.cn.basedialog.mvp.presenter;


import com.alibaba.fastjson.JSON;
import com.lcl.greendao.bean.DanmakuInfo;
import com.lcl.greendao.dao.DanmakuInfoDao;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.mvp.contract.DanmukuContract;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liancl on 2017/11/28.
 */

public class VideoPlayerPresenter extends RxPresenter<DanmukuContract.View> implements DanmukuContract.Present {


    private DanmakuInfoDao danmakuInfoDao;
    private VideoInfo mVideoData;

    public VideoPlayerPresenter(DanmakuInfoDao danmakuInfoDao,VideoInfo videoData) {
        this.danmakuInfoDao = danmakuInfoDao;
        this.mVideoData = videoData;
    }

    @Override
    public void addDanmaku(DanmakuInfo danmakuInfo) {
        //将弹幕保存
        danmakuInfoDao.insert(danmakuInfo);

        //将弹幕转为流
        List<DanmakuInfo> list = danmakuInfoDao.queryBuilder().where(DanmakuInfoDao.Properties.Vid.eq(mVideoData.getVid())).list();
        Observable.just(list)
                .map(new Function<List<DanmakuInfo>, InputStream>() {
                    @Override
                    public InputStream apply(List<DanmakuInfo> danmakuInfos) throws Exception {
                        String jsonStr= JSON.toJSONString(danmakuInfos);
                        return new ByteArrayInputStream(jsonStr.getBytes());
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<InputStream>() {
                    @Override
                    public void accept(InputStream inputStream) throws Exception {
                        mView.loadDanmakuData(inputStream);
                    }
                });




    }

    @Override
    public void cleanDanmaku() {

    }
}
