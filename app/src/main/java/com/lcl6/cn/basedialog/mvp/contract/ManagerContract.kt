package com.lcl6.cn.basedialog.mvp.contract

import com.lcl6.cn.component.base.mvp.presnenter.BasePresenter
import com.lcl6.cn.component.base.mvp.view.BaseView

/**
 * Created by liancl on 2017/9/1.
 */

interface ManagerContract {
    interface Presenter : BasePresenter<View> {
        fun requestTabaoData()
        fun setGlobal(newUrl: String)
        fun removeGlobal()
        fun requestGuthub(newUrl: String)
        fun requestGank(newUrl: String)
        fun requestDouban(newUrl: String)
    }

    interface View : BaseView {
        fun requestTabaoData(message: String)
        fun setGlobal(message: String)
        fun removeGlobal(message: String)
        fun requestGuthub(message: String)
        fun requestGank(message: String)
        fun requestDouban(message: String)
    }
}
