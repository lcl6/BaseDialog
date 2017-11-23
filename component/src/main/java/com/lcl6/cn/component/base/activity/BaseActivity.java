package com.lcl6.cn.component.base.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.lcl6.cn.component.R;
import com.lcl6.cn.component.event.TextEvent;
import com.lcl6.cn.component.widget.net.NetworkStateView;
import com.lcl6.cn.component.widget.title.TitleBar;
import com.lcl6.cn.component.widget.title.TitleConfig;
import com.lcl6.cn.utils.ToastUtils;
import com.lcl6.cn.utils.statusbar.QMUIStatusBarHelper;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无mvp的基类
 * Created by liancl on 2017/9/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements NetworkStateView.OnRefreshListener {

    private NetworkStateView mNetworkStateView;

    public Unbinder mUnbinder;
    public TitleBar mTitleBar;
    private ImageView mCollectView;

    /***配置*/
    TitleConfig mTtitleConfig;
    /**配置的bulider*/
    TitleConfig.Buidler mBuidler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        afterCreat(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
        initIntentData();
        initData();
        initViewListener();

    }

    /**
     * 初始化网络的状态
     */
    private void initNetWork() {
        mNetworkStateView.setOnRefreshListener(this);
        mNetworkStateView.showLoading();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        super.setContentView(view);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.fl_contain);
        mNetworkStateView = (NetworkStateView) view.findViewById(R.id.nsv_state_view);
        mTitleBar= (TitleBar)view.findViewById(R.id.title_bar);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);
        frameLayout.addView(childView, 0);
        initNetWork();
        initTitle();
    }

    private void initTitle() {
        boolean immeisve = QMUIStatusBarHelper.immeisve(this);
        mBuidler= new TitleConfig.Buidler();
        mTtitleConfig = mBuidler
                .setImmersive(immeisve)
                .setLeftText("返回")
                .setLeftVisible(true)
                .setLeftTextColor(Color.WHITE)
                .setLeftImageResource(R.drawable.ic_back)
                .setTitle("标题")
                .setDividerColor(Color.GRAY)
                .setTitleColor(Color.WHITE)
                .setSubTitleColor(Color.WHITE)
                .setTitleBackground(Color.BLUE)
                .setActionTextColor(Color.WHITE)
                .setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorPrimary))
                .builde();
        mTitleBar.setTitleConfig(mTtitleConfig);

        mTitleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backFinish();
            }
        });
        mCollectView = (ImageView) mTitleBar.addAction(new TitleBar.ImageAction(R.drawable.ic_net_error) {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了s收藏");
                mCollectView.setImageResource(R.drawable.ic_refresh);
//                mTitleBar.setTitle( "文章详情\n朋友圈" );
//                mIsSelected = !mIsSelected;
            }
        });

        mTitleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                ToastUtils.showShort("点击了发布");
            }
        });


    }

    /**点击返回按钮*/
    public void backFinish(){
        finish();
    }

    /**获取TitleConfig*/
    public TitleConfig getTitleConfig(){
        return mTtitleConfig;
    }

    /**获取TitleConfig  Bulider   便于子类扩展*/
    public TitleConfig.Buidler getTitleConfigBuilder(){
       return mBuidler;
    }

    /**获取titlebar*/
    public TitleBar getTitleBar(){
        return mTitleBar;
    }
    /**隐藏titlebar*/
    public void setTitleBarGone(){
        if (mTitleBar == null) {
            return;
        }
        mTitleBar.setVisibility(View.GONE);
    }

    public Context getContext() {
        return this;
    }

    protected abstract int setLayoutId();

    protected void beforeCreatView() {
    }

    protected void afterCreat(Bundle savedInstanceState) {

    }

    protected abstract void initView();

    protected void initIntentData() {
    }

    protected abstract void initData();

    protected void initViewListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if(mUnbinder!=null){
            mUnbinder.unbind();
        }
        mNetworkStateView.setOnRefreshListener(null);
    }

    @Override
    public void onRefresh() {

    }

    /**
     * 显示正在加载中
     */
    public void onLoadingStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showLoading();
    }

    /**
     * 显示加载成功
     */
    public void onLoadSuccessStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showSuccess();
    }

    /**
     * 显示错误状态
     */
    public void onLoadErrorStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showError();
    }

    /**
     * 显示无数据状态
     */
    public void onLoadEmptyStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showEmpty();
    }

    /**
     * 显示没有网络状态
     */
    public void onLoadNoNetStatus() {
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showNoNetwork();
    }

    @Subscribe
    public void onEvent(TextEvent event) {
    }
}
