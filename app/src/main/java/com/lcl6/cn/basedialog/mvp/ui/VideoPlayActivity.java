package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import com.dl7.player.danmaku.OnDanmakuListener;
import com.dl7.player.media.IjkPlayerView;
import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.bean.damaku.DanmakuInfo;
import com.lcl6.cn.basedialog.engine.DanmakuConverter;
import com.lcl6.cn.basedialog.engine.DanmakuLoader;
import com.lcl6.cn.basedialog.engine.DanmakuParser;
import com.lcl6.cn.component.base.activity.BaseMvpActivity;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import butterknife.BindView;

/**
 * Created by liancl on 2017/11/23.
 */

public class VideoPlayActivity extends BaseMvpActivity {
    public static final String EXTRA_VIDEO="video_data";
    VideoInfo mVideoInfo;

    @BindView(R.id.video_player)
    IjkPlayerView mPlayerView;


    public static void start(Context context, VideoInfo data) {
        Intent starter = new Intent(context, VideoPlayActivity.class);
        starter.putExtra(EXTRA_VIDEO,data);
        context.startActivity(starter);
    }

    @Override
    protected RxPresenter getPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activty_video_detail;
    }

    @Override
    protected void beforeCreatView() {
        super.beforeCreatView();
        getIntentData();
    }

    private void getIntentData() {
        mVideoInfo = (VideoInfo) getIntent().getSerializableExtra(EXTRA_VIDEO);

    }

    @Override
    protected void initView() {
        stateSuccess();
        setTitleBarGone();
        initPlayer();

    }

    private void initPlayer() {
        mPlayerView.init().setTitle(mVideoInfo.getTitle())
                .setVideoSource(null, mVideoInfo.getMp4_url(), mVideoInfo.getMp4Hd_url(), null, null)
                .enableDanmaku(true)
                .setDanmakuCustomParser(new DanmakuParser(), DanmakuLoader.instance(), DanmakuConverter.instance())
                .setDanmakuListener(new OnDanmakuListener<DanmakuInfo>() {
                    @Override
                    public boolean isValid() {
                        return true;
                    }

                    @Override
                    public void onDataObtain(DanmakuInfo danmakuInfo) {
                        danmakuInfo.setUserName("lcl");
                        danmakuInfo.setVid(mVideoInfo.getVid());
                        //数据库  待会儿添加
//                        mPresenter.addDanmaku(danmakuInfo);
                    }
                });
    }

    @Override
    protected void initData() {

    }



    @Override
    protected void onResume() {
        super.onResume();
        if(mPlayerView!=null){
            mPlayerView.onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mPlayerView!=null){
            mPlayerView.onPause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPlayerView!=null){
            mPlayerView.onDestroy();
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(mPlayerView!=null){
            mPlayerView.configurationChanged(newConfig);
        }
    }

}
