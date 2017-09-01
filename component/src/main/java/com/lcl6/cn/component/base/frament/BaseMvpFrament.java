package com.lcl6.cn.component.base.frament;

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
