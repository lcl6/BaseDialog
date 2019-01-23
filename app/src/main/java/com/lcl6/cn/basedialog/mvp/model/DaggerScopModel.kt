package com.lcl6.cn.basedialog.mvp.model

/**
 * Created by liancl on 2017/8/31.
 */

interface DaggerScopModel {
    fun getData(listener: LoadCompleteListener)
    interface LoadCompleteListener {
        fun onComplete(list: List<String>)
    }
}
