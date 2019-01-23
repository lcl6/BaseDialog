package com.lcl6.cn.basedialog.mvp.model

import com.lcl6.cn.basedialog.bean.JsoupBean

/**
 * Created by liancl on 2017/8/23.
 */

interface NewsModel {

    fun getData(listener: LoadCompleteListener)

    interface LoadCompleteListener {
        fun comlpete(list: List<JsoupBean>)
    }


}
