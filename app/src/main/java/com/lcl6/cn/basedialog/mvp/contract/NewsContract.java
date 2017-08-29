package com.lcl6.cn.basedialog.mvp.contract;

import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter;
import com.lcl6.cn.component.base.mvp.view.BaseView;

import java.util.List;

/**
 * Created by liancl on 2017/8/23.
 */

public interface NewsContract {


    interface View extends BaseView {
        void showContent(List<JsoupBean> list);
    }
    interface Presenter extends BasePresenter<View> {
        void getData();
    }
}
