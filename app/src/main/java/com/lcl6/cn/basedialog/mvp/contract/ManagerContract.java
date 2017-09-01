package com.lcl6.cn.basedialog.mvp.contract;

import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

/**
 * Created by liancl on 2017/9/1.
 */

public interface ManagerContract {
    interface Presenter extends BasePresenter<View>{
        void requestTabaoData();
        void setGlobal(String newUrl);
        void removeGlobal();
        void requestGuthub( String newUrl);
        void requestGank(String newUrl);
        void requestDouban(String newUrl);
    }

    interface View extends BaseView{
        void requestTabaoData(String message);
        void setGlobal(String message);
        void removeGlobal(String message);
        void requestGuthub( String message);
        void requestGank(String message);
        void requestDouban(String message);
    }
}
