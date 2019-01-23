package com.lcl6.cn.basedialog.mvp.ui

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.basedialog.adapter.VideoAdapter
import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.basedialog.di.component.DaggerVideoComponent
import com.lcl6.cn.basedialog.di.model.VideoModule
import com.lcl6.cn.basedialog.mvp.contract.VideoListConstract
import com.lcl6.cn.basedialog.mvp.presenter.VideoListPresent
import com.lcl6.cn.component.base.activity.BaseMvpActivity
import javax.inject.Inject

/**
 * Created by liancl on 2017/11/22.
 */

class VideoActivity : BaseMvpActivity<VideoListPresent>(), VideoListConstract.View {
    @Inject
    internal var mVideoListPresent: VideoListPresent? = null
    @BindView(R.id.recyclerView)
    internal var mRecyclerView: RecyclerView? = null

    @Inject
    internal var mAdapter: VideoAdapter? = null


    override fun getPresenter(): VideoListPresent? {
        DaggerVideoComponent.builder().videoModule(VideoModule(this)).build().inject(this)
        return mVideoListPresent
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_video
    }

    override fun initView() {

        val builde = titleConfigBuilder
                .setTitle(getString(R.string.app_vedio))
                .removeRightView(true)
                .builde()
        mTitleBar.setTitleConfig(builde)

        mRecyclerView!!.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView!!.adapter = mAdapter

    }

    override fun initData() {
        mVideoListPresent!!.getData()
        stateSuccess()
    }

    override fun initViewListener() {
        super.initViewListener()
        if (mAdapter != null) {
            mAdapter!!.setOnItemClickListener { viewHolder, item, position -> VideoPlayActivity.start(context, item) }

        }
    }

    override fun showContent(list: List<VideoInfo>) {
        mAdapter!!.setData(list)
        mAdapter!!.notifyDataSetChanged()
    }

    override fun stateError() {
        super.stateError()

    }

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, VideoActivity::class.java)
            context.startActivity(starter)
        }
    }
}
