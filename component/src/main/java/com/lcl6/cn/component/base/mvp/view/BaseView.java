package com.lcl6.cn.component.base.mvp.view;

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
}
