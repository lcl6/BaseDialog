package com.lcl6.cn.component.base.mvp.view;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created by liancl on 2017/8/23.
 */

public interface BaseView {
    void showErrorMsg(String msg);
    //=======  State  =======
    void stateError();
    void stateEmpty();
    void stateLoading();
    void stateSuccess();
    /**
     * 绑定生命周期
     * @param <T>
     * @return
     */
    <T> LifecycleTransformer<T> bindToLife();
}
