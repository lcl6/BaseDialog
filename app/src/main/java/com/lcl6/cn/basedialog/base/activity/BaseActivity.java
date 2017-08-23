package com.lcl6.cn.basedialog.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcl6.cn.basedialog.base.view.BaseView;
import com.lcl6.cn.basedialog.base.presnenter.RxPresenter;

import butterknife.ButterKnife;

/**
 * Created by liancl on 2017/8/23.
 */

public abstract class BaseActivity <T extends RxPresenter>extends Activity implements BaseView{

    public T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        mPresenter=getPresenter();
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

    public Context getContext(){
        return this;
    }
    protected abstract int setLayoutId();

    protected  void initIntentData(){}

    protected  void beforeCreatView(){ }

    protected  void initViewListener(){}

    protected abstract void initData();

    protected abstract void initView();

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
