package com.lcl6.cn.component.base.mvp.presnenter;


import com.lcl6.cn.component.base.mvp.view.BaseView;

/**
 * Created by liancl on 2017/8/23.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void dechView();
}
