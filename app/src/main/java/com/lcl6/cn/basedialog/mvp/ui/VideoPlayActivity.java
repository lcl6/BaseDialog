package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.ccc.danmuibrary.OnDanmakuListener;
import com.dl7.player.media.IjkPlayerView;
import com.lcl.greendao.GreenDaoManager;
import com.lcl.greendao.bean.DanmakuInfo;
import com.lcl.greendao.dao.DanmakuInfoDao;
import com.lcl.greendao.dao.DaoSession;
import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.di.component.DaggerVideoComponent;
import com.lcl6.cn.basedialog.di.model.VideoModule;
import com.lcl6.cn.basedialog.engine.DanmakuConverter;
import com.lcl6.cn.basedialog.engine.DanmakuLoader;
import com.lcl6.cn.basedialog.engine.DanmakuParser;
import com.lcl6.cn.basedialog.mvp.contract.DanmukuContract;
import com.lcl6.cn.basedialog.mvp.presenter.VideoPlayerPresenter;
import com.lcl6.cn.basedialog.widget.SimpleButton;
import com.lcl6.cn.component.base.activity.BaseMvpActivity;

import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by liancl on 2017/11/23.
 */

public class VideoPlayActivity extends BaseMvpActivity<VideoPlayerPresenter> implements DanmukuContract.View{
    public static final String EXTRA_VIDEO="video_data";
    VideoInfo mVideoInfo;

    @BindView(R.id.video_player)
    IjkPlayerView mPlayerView;

    @BindView(R.id.sb_send)
    SimpleButton mSbSend;

    @BindView(R.id.et_content)
    EditText mEtContent;

    /**弹幕**/
    DanmakuInfoDao danmakuInfoDao;

    @Inject
    VideoPlayerPresenter mPresent;


    public static void start(Context context, VideoInfo data) {
        Intent starter = new Intent(context, VideoPlayActivity.class);
        starter.putExtra(EXTRA_VIDEO,data);
        context.startActivity(starter);
    }

    @Override
    protected VideoPlayerPresenter getPresenter() {
        getIntentData();
        initDao();
        DaggerVideoComponent.builder().videoModule(new VideoModule(danmakuInfoDao,mVideoInfo)).build().inject(this);
        return mPresent;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activty_video_detail;
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

    private void initDao() {
        GreenDaoManager.get().init(getContext());
        DaoSession daoSession = GreenDaoManager.get().getDaoSession();
        if(daoSession!=null){
            danmakuInfoDao = daoSession.getDanmakuInfoDao();
            Log.e("","");
        }


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
                        mPresenter.addDanmaku(danmakuInfo);
                    }
                });
    }

    @Override
    protected void initData() {

    }



    @OnClick({R.id.sb_send})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.sb_send:
                mPlayerView.sendDanmaku(mEtContent.getText().toString(),false);
                mEtContent.setText("");
                break;
        }
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

    @Override
    public void loadData(VideoInfo data) {
        mVideoInfo = data;

    }

    @Override
    public void loadDanmakuData(InputStream inputStream) {
        mPlayerView.setDanmakuSource(inputStream);
    }
}
