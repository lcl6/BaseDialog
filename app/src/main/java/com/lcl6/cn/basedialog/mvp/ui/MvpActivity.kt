package com.lcl6.cn.basedialog.mvp.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import butterknife.BindView
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.basedialog.adapter.mvp.MvpListAdapter
import com.lcl6.cn.basedialog.app.App
import com.lcl6.cn.basedialog.bean.JsoupBean
import com.lcl6.cn.basedialog.di.component.DaggerActivityComponent
import com.lcl6.cn.basedialog.di.model.ActivityModule
import com.lcl6.cn.basedialog.mvp.contract.NewsContract
import com.lcl6.cn.basedialog.mvp.presenter.NewsPresenter
import com.lcl6.cn.component.base.activity.BaseMvpActivity
import javax.inject.Inject

/**
 * Created by liancl on 2017/8/23.
 */

class MvpActivity : BaseMvpActivity<NewsPresenter>(), NewsContract.View {
    @BindView(R.id.recyclerView)
    internal var mRecyclerView: RecyclerView? = null

    internal lateinit var mAdapter: MvpListAdapter

    @Inject
    internal var mNewsPresenter: NewsPresenter? = null

    override fun getPresenter(): NewsPresenter? {
        //还需要注入
        DaggerActivityComponent.builder().appComponent(App.getAppComponent()).activityModule(ActivityModule(1)).build().inject(this)
        return mNewsPresenter
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_mvp
    }

    override fun initData() {
        initPostData()
    }

    private fun initPostData() {
        mNewsPresenter!!.getData()
    }

    override fun initView() {
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayout.VERTICAL
        mRecyclerView!!.layoutManager = manager
        mAdapter = MvpListAdapter(context)
        mRecyclerView!!.adapter = mAdapter
    }

    override fun showContent(list: List<JsoupBean>) {
        mAdapter.setData(list)
        mAdapter.notifyDataSetChanged()
        onLoadSuccessStatus()
    }


    override fun onDestroy() {
        super.onDestroy()
    }

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, MvpActivity::class.java)
            context.startActivity(starter)
        }
    }
}
