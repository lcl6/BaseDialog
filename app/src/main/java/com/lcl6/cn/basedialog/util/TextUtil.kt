package com.lcl6.cn.basedialog.util

import android.content.Context
import android.widget.TextView

/**
 * Created by liancl on 2017/10/23.
 */

object TextUtil {
    operator fun get(context: Context): String {
        val textView = TextView(context)
        textView.text = "你好"
        return textView.text.toString()
    }
}
