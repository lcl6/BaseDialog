package com.lcl6.cn.component.base.mvp.presnenter;


import com.lcl6.cn.component.base.mvp.view.BaseView;

/**
 * Created by liancl on 2017/8/23.
 */

public abstract class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void dechView() {
        this.mView =null;
    }
}
