package com.lcl6.cn.basedialog.base.view;

/**
 * Created by liancl on 2017/8/23.
 */

public interface BaseView<T> {
    void showErrorMsg(String msg);
    //=======  State  =======
    void stateError();
    void stateEmpty();
    void stateLoading();
    void stateSuccess();
}
