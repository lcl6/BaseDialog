package com.lcl6.cn.component.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;
import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 *
 * Created by liancl on 2017/8/23.
 */

public abstract class BaseMvpActivity<T extends RxPresenter> extends BaseActivity implements BaseView {

    public T mPresenter;
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
        if (mPresenter != null) {
            mPresenter.dechView();
        }
        super.onDestroy();
    }

    @Override
    public void stateError() {
        onLoadErrorStatus();
    }

    @Override
    public void stateEmpty() {
        onLoadEmptyStatus();
    }

    @Override
    public void stateLoading() {

        onLoadingStatus();
    }

    @Override
    public void stateSuccess() {
        onLoadSuccessStatus();
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }
}
