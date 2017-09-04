package com.lcl6.cn.component.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.lcl6.cn.component.R;
import com.lcl6.cn.component.widget.net.NetworkStateView;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无mvp的基类
 * Created by liancl on 2017/9/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity implements  NetworkStateView.OnRefreshListener {

    NetworkStateView mNetworkStateView;

    Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        afterCreat(savedInstanceState);
        mUnbinder = ButterKnife.bind(this);
        initView();
        initIntentData();
        initData();
        initViewListener();

    }

    /**初始化网络的状态*/
    private void initNetWork() {
        mNetworkStateView.setOnRefreshListener(this);
        mNetworkStateView.showLoading();
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        super.setContentView(view);
        FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.fl_contain);
        mNetworkStateView= (NetworkStateView)view.findViewById(R.id.nsv_state_view);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);
        frameLayout.addView(childView,0);
        initNetWork();
    }

    public Context getContext() {
        return this;
    }

    protected abstract int setLayoutId();

    protected void beforeCreatView() {
    }
    protected void afterCreat(Bundle savedInstanceState){

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
        mUnbinder.unbind();
    }

    @Override
    public void onRefresh() {

    }

    /**显示正在加载中*/
    public void onLoadingStatus(){
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showLoading();
    }
    /**显示加载成功*/
    public void onLoadSuccessStatus(){
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showSuccess();
    }
    /**显示错误状态*/
    public void onLoadErrorStatus(){
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showError();
    }
    /**显示无数据状态*/
    public void onLoadEmptyStatus(){
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showEmpty();
    }
    /**显示没有网络状态*/
    public void onLoadNoNetStatus(){
        if (mNetworkStateView == null) {
            return;
        }
        mNetworkStateView.showNoNetwork();
    }

}
