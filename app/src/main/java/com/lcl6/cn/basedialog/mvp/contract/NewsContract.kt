package com.lcl6.cn.basedialog.mvp.contract

import com.lcl6.cn.basedialog.bean.JsoupBean
import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter
import com.lcl6.cn.component.base.mvp.view.BaseView

/**
 * Created by liancl on 2017/8/23.
 */

interface NewsContract {


    interface View : BaseView {
        fun showContent(list: List<JsoupBean>)
    }

    interface Presenter : BasePresenter<View> {
        fun getData()
    }
}
