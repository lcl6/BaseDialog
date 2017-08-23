package com.lcl6.cn.basedialog.mvp.view;

import com.lcl6.cn.basedialog.base.view.BaseView;
import com.lcl6.cn.basedialog.bean.JsoupBean;

import java.util.List;

/**
 *
 * Created by liancl on 2017/8/23.
 */

public interface NewsView extends BaseView{
    void showContent(List<JsoupBean> list);
}
