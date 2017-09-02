package com.lcl6.cn.component.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

import butterknife.Unbinder;


/**
 *
 * Created by liancl on 2017/8/23.
 */

public abstract class BaseMvpActivity<T extends RxPresenter> extends BaseActivity implements BaseView {

    public T mPresenter;
    Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        super.onCreate(savedInstanceState);

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

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
