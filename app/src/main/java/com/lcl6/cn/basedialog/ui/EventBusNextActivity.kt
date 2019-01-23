package com.lcl6.cn.basedialog.ui

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.basedialog.event.ClickEvent
import com.lcl6.cn.basedialog.util.OnClickEvent
import com.lcl6.cn.component.base.activity.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by liancl on 2018/5/11.
 */

class EventBusNextActivity : BaseActivity() {

    @BindView(R.id.tv_next)
    internal var tvNext: TextView? = null

    override fun setLayoutId(): Int {
        return R.layout.activity_event_next
    }

    override fun initView() {
        ButterKnife.bind(this)
        tvNext!!.setOnClickListener(object : OnClickEvent() {
            override fun singleClick(v: View) {

                finish()
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().removeAllStickyEvents()
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun finich(event: ClickEvent) {
        com.blankj.utilcode.util.ToastUtils.showLong("接受到了")
        //        finish();
    }

    override fun initData() {
        onLoadSuccessStatus()
    }

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, EventBusNextActivity::class.java)
            context.startActivity(starter)
        }
    }

}
