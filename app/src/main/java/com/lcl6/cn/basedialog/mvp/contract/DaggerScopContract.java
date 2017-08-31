package com.lcl6.cn.basedialog.mvp.contract;

import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

import java.util.List;

/**
 * Created by liancl on 2017/8/31.
 */

public interface DaggerScopContract {

    interface View extends BaseView{
        void showContent(List<String> list);
    }

    interface Present extends BasePresenter<View>{
        void getData();
    }

}
