package com.lcl6.cn.component.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

import butterknife.ButterKnife;


/**
 * mvp的基类
 * Created by liancl on 2017/8/23.
 */

public abstract class BaseMvpActivity<T extends RxPresenter> extends AppCompatActivity implements BaseView {

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        mPresenter = getPresenter();
        initView();
        initIntentData();
        initData();
        initViewListener();
    }

    protected abstract T getPresenter();

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.dechView();
        }
    }

    public Context getContext() {
        return this;
    }

    protected abstract int setLayoutId();

    protected void beforeCreatView() {
    }

    protected abstract void initView();

    protected void initIntentData() {
    }

    protected abstract void initData();

    protected void initViewListener() {
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateSuccess() {

    }

    @Override
    public void showErrorMsg(String msg) {

    }
}
