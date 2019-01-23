package com.lcl6.cn.basedialog.mvp.contract

import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter
import com.lcl6.cn.component.base.mvp.view.BaseView

/**
 * Created by liancl on 2017/8/31.
 */

interface DaggerScopContract {

    interface View : BaseView {
        fun showContent(list: List<String>)

    }

    interface Present : BasePresenter<View> {
        fun getData()
    }

}
