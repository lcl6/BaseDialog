package com.lcl6.cn.basedialog.mvp.presenter

import com.lcl6.cn.basedialog.mvp.contract.ManagerContract
import com.lcl6.cn.basedialog.mvp.model.ManagerModel
import com.lcl6.cn.basedialog.mvp.model.impl.ManagerModelImpl
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter

/**
 * Created by liancl on 2017/9/1.
 */

class ManagerPresenter : RxPresenter<ManagerContract.View>(), ManagerContract.Presenter {
    //    @Inject
    internal var managerModel: ManagerModelImpl

    init {
        this.managerModel = ManagerModelImpl()
    }

    override fun requestTabaoData() {
        managerModel.requestTabaoData(object : ManagerModel.LoadCompleteListener {
            override fun onComplete(successMessage: String) {
                mView.requestTabaoData(successMessage)
            }

            override fun onFail(errorMessage: String) {
                mView.requestTabaoData(errorMessage)
            }
        })
    }

    override fun setGlobal(newUrl: String) {
        managerModel.setGlobal(object : ManagerModel.LoadCompleteListener {
            override fun onComplete(successMessage: String) {
                mView.setGlobal(successMessage)
            }

            override fun onFail(errorMessage: String) {
                mView.setGlobal(errorMessage)
            }
        }, newUrl)
    }

    override fun removeGlobal() {
        managerModel.requestTabaoData(object : ManagerModel.LoadCompleteListener {
            override fun onComplete(successMessage: String) {
                mView.requestTabaoData(successMessage)
            }

            override fun onFail(errorMessage: String) {
                mView.requestTabaoData(errorMessage)
            }
        })
    }

    override fun requestGuthub(newUrl: String) {
        managerModel.requestGuthub(object : ManagerModel.LoadCompleteListener {
            override fun onComplete(successMessage: String) {
                mView.requestGuthub(successMessage)
            }

            override fun onFail(errorMessage: String) {
                mView.requestGuthub(errorMessage)
            }
        }, newUrl)
    }

    override fun requestGank(newUrl: String) {
        managerModel.requestGank(object : ManagerModel.LoadCompleteListener {
            override fun onComplete(successMessage: String) {
                mView.requestGank(successMessage)
            }

            override fun onFail(errorMessage: String) {
                mView.requestGank(errorMessage)
            }
        }, newUrl)
    }

    override fun requestDouban(newUrl: String) {
        managerModel.requestDouban(object : ManagerModel.LoadCompleteListener {
            override fun onComplete(successMessage: String) {
                mView.requestDouban(successMessage)
            }

            override fun onFail(errorMessage: String) {
                mView.requestDouban(errorMessage)
            }
        }, newUrl)
    }
}
