package com.lcl6.cn.basedialog.mvp.contract;

import com.lcl6.cn.basedialog.base.presnenter.BasePresenter;
import com.lcl6.cn.basedialog.base.view.BaseView;
import com.lcl6.cn.basedialog.bean.JsoupBean;

import java.util.List;

/**
 * Created by liancl on 2017/8/23.
 */

public interface NewsContract {


    interface View extends BaseView{
        void showContent(List<JsoupBean> list);
    }
    interface Presenter extends BasePresenter<View>{
        void getData();
    }
}
