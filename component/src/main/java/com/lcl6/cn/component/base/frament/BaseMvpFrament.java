package com.lcl6.cn.component.base.frament;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

/**
 * Created by liancl on 2017/8/29.
 */

public abstract class BaseMvpFrament<T extends RxPresenter> extends LazyFragment implements BaseView {
    public T mPresenter;
    @Override
    protected int getAbsLayoutId() {
        return getLayoutId();
    }

    public abstract int getLayoutId();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = getPresenter();
    }

    protected abstract T getPresenter();
    @Override
    public void showErrorMsg(String msg) {

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
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.dechView();
        }

        super.onDestroyView();

    }

    @Override
    protected void onFragmentResume() {
        super.onFragmentResume();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }
}
