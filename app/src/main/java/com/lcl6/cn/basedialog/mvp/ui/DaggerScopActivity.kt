package com.lcl6.cn.basedialog.mvp.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.basedialog.adapter.EdtextAdapter
import com.lcl6.cn.basedialog.di.bean.MyClassB
import com.lcl6.cn.basedialog.mvp.contract.DaggerScopContract
import com.lcl6.cn.basedialog.mvp.presenter.DaggerScopPresent
import com.lcl6.cn.component.base.activity.BaseMvpActivity
import com.lcl6.cn.utils.ToastUtils
import java.util.*
import javax.inject.Inject

/**
 * Created by liancl on 2017/8/30.
 */

class DaggerScopActivity : BaseMvpActivity<DaggerScopPresent>(), DaggerScopContract.View {

    @Inject
    internal var classB: MyClassB? = null

    @BindView(R.id.recyclerView)
    internal var mRecyclerView: RecyclerView? = null

    internal var mAdapter: EdtextAdapter? = null

    internal lateinit var mlist: MutableList<String>
    override fun getPresenter(): DaggerScopPresent {
        return DaggerScopPresent()
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_dagger_list
    }

    override fun initView() {
        val titleBar = titleBar
        titleBar.removeAllActions(true)
        val builde = titleConfigBuilder.setLeftVisible(false).builde()
        titleBar.setTitleConfig(builde)
        mlist = ArrayList()
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mRecyclerView!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mAdapter = EdtextAdapter(this)
        mAdapter!!.setData(mlist)
        mRecyclerView!!.adapter = mAdapter
    }

    override fun initData() {
        mPresenter.getData()
    }

    override fun initViewListener() {
        super.initViewListener()
        if (mAdapter != null) {
            mAdapter!!.setOnItemClickListener { viewHolder, item, position -> ToastUtils.showShort("点击了$position") }

        }
    }

    override fun showContent(list: List<String>) {
        mlist.clear()
        mlist.addAll(list)
        mAdapter!!.notifyDataSetChanged()
        onLoadSuccessStatus()
    }

    companion object {
        fun start(context: Context) {
            val starter = Intent(context, DaggerScopActivity::class.java)
            context.startActivity(starter)
        }
    }

}
