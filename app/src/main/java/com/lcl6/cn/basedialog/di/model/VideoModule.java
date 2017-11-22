package com.lcl6.cn.basedialog.di.model;

import android.content.Context;

import com.lcl6.cn.basedialog.adapter.VideoAdapter;
import com.lcl6.cn.basedialog.mvp.contract.VideoListConstract;
import com.lcl6.cn.basedialog.mvp.presenter.VideoListPresent;

import dagger.Module;
import dagger.Provides;

/**
 * Created by liancl on 2017/11/22.
 */

@Module
public class VideoModule {

    private Context context;
    private VideoListConstract.View mView;

//    @Inject
//    VideoListPresent mVideoListPresent;


    public VideoModule(Context context, VideoListConstract.View view) {
        this.context = context;
        mView = view;
    }

    @Provides
    public VideoListPresent provideVideoListModelImpl(){
        return new VideoListPresent(mView);
    }

    @Provides
    public VideoAdapter provideVideoAdapter(){
        return new VideoAdapter(context);
    }


}
