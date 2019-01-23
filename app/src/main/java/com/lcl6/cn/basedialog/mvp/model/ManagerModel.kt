package com.lcl6.cn.basedialog.mvp.model

/**
 * Created by liancl on 2017/9/1.
 */

interface ManagerModel {
    fun requestTabaoData(listener: LoadCompleteListener)
    fun setGlobal(listener: LoadCompleteListener, newUrl: String)
    fun removeGlobal(listener: LoadCompleteListener)
    fun requestGuthub(listener: LoadCompleteListener, newUrl: String)
    fun requestGank(listener: LoadCompleteListener, newUrl: String)
    fun requestDouban(listener: LoadCompleteListener, newUrl: String)

    interface LoadCompleteListener {
        fun onComplete(successMessage: String)
        fun onFail(errorMessage: String)
    }

}
