package com.lcl6.cn.basedialog.mvp.presenter

import com.lcl6.cn.basedialog.mvp.contract.DaggerScopContract
import com.lcl6.cn.basedialog.mvp.model.DaggerScopModel
import com.lcl6.cn.basedialog.mvp.model.impl.DaggerScopModelImpl
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter

/**
 * Created by liancl on 2017/8/31.
 */

class DaggerScopPresent : RxPresenter<DaggerScopContract.View>(), DaggerScopContract.Present {
    internal var daggerScopModel: DaggerScopModelImpl

    init {
        daggerScopModel = DaggerScopModelImpl()
    }

    override fun getData() {
        daggerScopModel.getData(object : DaggerScopModel.LoadCompleteListener {
            override fun onComplete(list: List<String>) {
                if (mView != null) {
                    mView.showContent(list)
                }
            }
        })
    }
}
