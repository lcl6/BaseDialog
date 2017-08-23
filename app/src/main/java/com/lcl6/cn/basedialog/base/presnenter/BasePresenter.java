package com.lcl6.cn.basedialog.base.presnenter;

import com.lcl6.cn.basedialog.base.view.BaseView;

/**
 * Created by liancl on 2017/8/23.
 */

public interface BasePresenter<T extends BaseView> {

    void attachView(T view);

    void dechView();
}
