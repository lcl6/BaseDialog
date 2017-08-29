package com.lcl6.cn.component.base.frament;

import android.os.Bundle;
import android.view.View;

import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

/**
 * Created by liancl on 2017/8/29.
 */

public abstract class BaseMvpFrament<T extends RxPresenter> extends LazyFragment implements BaseView {

    @Override
    protected int getAbsLayoutId() {
        return getLayoutId();
    }

    public abstract int getLayoutId();
    @Override
    protected void findViews(View view, Bundle savedInstanceState) {

    }

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
}
