package com.lcl6.cn.basedialog.util

import android.view.View
import android.widget.Toast
import com.lcl6.cn.basedialog.R
import java.util.*

/**
 * Created by liancl on 2017/12/18.
 */

class HookListener(private val originalClick: View.OnClickListener?) : View.OnClickListener {

    override fun onClick(v: View) {

        originalClick?.onClick(v)
        val sb = StringBuffer()
        sb.append("hook succeed./n")
        val obj = v.getTag(R.id.id_hook)
        if (obj != null && obj is HashMap<*, *> && !(obj as Map<*, *>).isEmpty()) {
            for ((key, value) in obj as Map<String, String>) {
                sb.append("key => ")
                        .append(key)
                        .append(" ")
                        .append("value => ")
                        .append(value)
                        .append("\n")
            }
        } else {
            sb.append("params => null\n")
        }
        Toast.makeText(v.context, sb.toString(), Toast.LENGTH_LONG).show()

    }
}
