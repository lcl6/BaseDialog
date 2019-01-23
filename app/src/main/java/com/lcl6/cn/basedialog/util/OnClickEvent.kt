package com.lcl6.cn.basedialog.util

import android.view.View

/**
 * 判断是不是点击多次
 * Created by liancl on 2018/5/11.
 */

abstract class OnClickEvent : View.OnClickListener {

    abstract fun singleClick(v: View)

    override fun onClick(v: View) {
        if (onDoubClick()) {
            return
        }
        singleClick(v)
    }

    fun onDoubClick(): Boolean {
        var flag = false
        val time = System.currentTimeMillis() - lastTime

        if (time < 1000) {
            flag = true
        }
        lastTime = System.currentTimeMillis()
        return flag
    }

    companion object {
        var lastTime: Long = 0
    }
}
