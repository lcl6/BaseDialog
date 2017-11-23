package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.adapter.VideoAdapter;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.di.component.DaggerVideoComponent;
import com.lcl6.cn.basedialog.di.model.VideoModule;
import com.lcl6.cn.basedialog.mvp.contract.VideoListConstract;
import com.lcl6.cn.basedialog.mvp.presenter.VideoListPresent;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;
import com.lcl6.cn.component.base.activity.BaseMvpActivity;
import com.lcl6.cn.component.widget.title.TitleConfig;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by liancl on 2017/11/22.
 */

public class VideoActivity extends BaseMvpActivity<VideoListPresent> implements VideoListConstract.View{
    @Inject
    VideoListPresent mVideoListPresent;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject
    VideoAdapter mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, VideoActivity.class);
        context.startActivity(starter);
    }


    @Override
    protected VideoListPresent getPresenter() {
        return mVideoListPresent;
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_video;
    }
    @Override
    protected void initView() {

        TitleConfig builde = getTitleConfigBuilder()
                .setTitle(getString(R.string.app_vedio))
                .removeRightView(true)
                .builde();
        mTitleBar.setTitleConfig(builde);
        DaggerVideoComponent.builder().videoModule(new VideoModule(getContext(),this)).build().inject(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);

    }
    @Override
    protected void initData() {
        mVideoListPresent.getData();
        stateSuccess();
    }

    @Override
    protected void initViewListener() {
        super.initViewListener();
        if(mAdapter!=null){
            mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<VideoInfo>() {
                @Override
                public void onItemClick(RecyclerView.ViewHolder viewHolder, VideoInfo item, int position) {
                    VideoPlayActivity.start(getContext(),item);
                }
            });

        }
    }

    @Override
    public void showContent(List<VideoInfo> list) {
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void stateError() {
        super.stateError();

    }
}
