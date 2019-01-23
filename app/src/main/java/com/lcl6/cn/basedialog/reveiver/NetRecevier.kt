package com.lcl6.cn.basedialog.reveiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

import com.lcl6.cn.basedialog.ui.activity.MainActivity
import com.lcl6.cn.utils.ToastUtils

/**
 * Created by liancl on 2019/1/8.
 */
class NetRecevier : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {

        ToastUtils.showLong("接收广播")
        if (intent.action == ACTION) {
            val intent1 = Intent(context, MainActivity::class.java)
            intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent1)
        }
    }

    companion object {
        internal val ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }
}
