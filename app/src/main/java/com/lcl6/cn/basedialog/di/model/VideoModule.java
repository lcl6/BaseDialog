package com.lcl6.cn.basedialog.di.model;

import android.content.Context;

import com.lcl.greendao.dao.DanmakuInfoDao;
import com.lcl6.cn.basedialog.adapter.VideoAdapter;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.mvp.model.impl.VideoListModelImpl;
import com.lcl6.cn.basedialog.mvp.presenter.VideoListPresent;
import com.lcl6.cn.basedialog.mvp.presenter.VideoPlayerPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/11/22.
 */

@Module
public class VideoModule {

    private Context context;
    private DanmakuInfoDao danmakuInfoDao;
    private VideoInfo videoData;

    public VideoModule(DanmakuInfoDao danmakuInfoDao,VideoInfo videoData) {
        this.danmakuInfoDao = danmakuInfoDao;
        this.videoData = videoData;
    }

    public VideoModule(Context context) {
        this.context = context;
    }


    @Provides
    public VideoListModelImpl provideVideoListModelImpl(){
        return new VideoListModelImpl();
    }

    @Provides
    public VideoListPresent provideVideoListPresent(){
        return new VideoListPresent();
    }

    @Provides
    public VideoAdapter provideVideoAdapter(){
        return new VideoAdapter(context);
    }


    @Provides
    VideoPlayerPresenter provideVideoPlayerPresenter(){
        return new VideoPlayerPresenter(danmakuInfoDao,videoData);
    }


}
