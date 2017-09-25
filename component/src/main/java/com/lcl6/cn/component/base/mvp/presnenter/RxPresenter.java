package com.lcl6.cn.component.base.mvp.presnenter;


import com.lcl6.cn.component.base.mvp.view.BaseView;

import java.lang.ref.WeakReference;

/**
 * base presenter
 * Created by liancl on 2017/8/23.
 */

public abstract class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T mView;
    private WeakReference<T> mViewRef;

    @Override
    public void attachView(T view) {
        this.mView = view;
        mViewRef = new WeakReference<>(view);
    }

    @Override
    public void dechView() {
        this.mView =null;
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef=null;
        }
    }
}
