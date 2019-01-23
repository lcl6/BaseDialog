package com.lcl6.cn.basedialog.mvp.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.util.Log
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.ccc.danmuibrary.OnDanmakuListener
import com.dl7.player.media.IjkPlayerView
import com.lcl.greendao.GreenDaoManager
import com.lcl.greendao.bean.DanmakuInfo
import com.lcl.greendao.dao.DanmakuInfoDao
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.basedialog.bean.VideoInfo
import com.lcl6.cn.basedialog.di.component.DaggerVideoComponent
import com.lcl6.cn.basedialog.di.model.VideoModule
import com.lcl6.cn.basedialog.engine.DanmakuConverter
import com.lcl6.cn.basedialog.engine.DanmakuLoader
import com.lcl6.cn.basedialog.engine.DanmakuParser
import com.lcl6.cn.basedialog.mvp.contract.DanmukuContract
import com.lcl6.cn.basedialog.mvp.presenter.VideoPlayerPresenter
import com.lcl6.cn.basedialog.widget.SimpleButton
import com.lcl6.cn.component.base.activity.BaseMvpActivity
import java.io.InputStream
import javax.inject.Inject

/**
 * Created by liancl on 2017/11/23.
 */

class VideoPlayActivity : BaseMvpActivity<VideoPlayerPresenter>(), DanmukuContract.View {
    internal lateinit var mVideoInfo: VideoInfo

    @BindView(R.id.video_player)
    internal var mPlayerView: IjkPlayerView? = null

    @BindView(R.id.sb_send)
    internal var mSbSend: SimpleButton? = null

    @BindView(R.id.et_content)
    internal var mEtContent: EditText? = null

    /**弹幕 */
    internal lateinit var danmakuInfoDao: DanmakuInfoDao

    @Inject
    internal var mPresent: VideoPlayerPresenter? = null

    override fun getPresenter(): VideoPlayerPresenter? {
        getIntentData()
        initDao()
        DaggerVideoComponent.builder().videoModule(VideoModule(danmakuInfoDao, mVideoInfo)).build().inject(this)
        return mPresent
    }

    override fun setLayoutId(): Int {
        return R.layout.activty_video_detail
    }


    private fun getIntentData() {
        mVideoInfo = intent.getSerializableExtra(EXTRA_VIDEO) as VideoInfo

    }

    override fun initView() {

        stateSuccess()
        setTitleBarGone()
        initPlayer()

    }

    private fun initDao() {
        GreenDaoManager.get().init(context)
        val daoSession = GreenDaoManager.get().daoSession
        if (daoSession != null) {
            danmakuInfoDao = daoSession.danmakuInfoDao
            Log.e("", "")
        }


    }

    private fun initPlayer() {
        mPlayerView!!.init().setTitle(mVideoInfo.title)
                .setVideoSource(null, mVideoInfo.mp4_url, mVideoInfo.mp4Hd_url, null, null)
                .enableDanmaku(true)
                .setDanmakuCustomParser(DanmakuParser(), DanmakuLoader.instance(), DanmakuConverter.instance())
                .setDanmakuListener(object : OnDanmakuListener<DanmakuInfo> {
                    override fun isValid(): Boolean {
                        return true
                    }

                    override fun onDataObtain(danmakuInfo: DanmakuInfo) {
                        danmakuInfo.userName = "lcl"
                        danmakuInfo.vid = mVideoInfo.vid
                        mPresenter.addDanmaku(danmakuInfo)
                    }
                })
    }

    override fun initData() {

    }


    @OnClick(R.id.sb_send)
    fun onClick(view: View) {
        when (view.id) {
            R.id.sb_send -> {
                mPlayerView!!.sendDanmaku(mEtContent!!.text.toString(), false)
                mEtContent!!.setText("")
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (mPlayerView != null) {
            mPlayerView!!.onResume()
        }

    }

    override fun onPause() {
        super.onPause()
        if (mPlayerView != null) {
            mPlayerView!!.onPause()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        if (mPlayerView != null) {
            mPlayerView!!.onDestroy()
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (mPlayerView != null) {
            mPlayerView!!.configurationChanged(newConfig)
        }
    }

    override fun loadData(data: VideoInfo) {
        mVideoInfo = data

    }

    override fun loadDanmakuData(inputStream: InputStream) {
        mPlayerView!!.setDanmakuSource(inputStream)
    }

    companion object {
        val EXTRA_VIDEO = "video_data"


        fun start(context: Context, data: VideoInfo) {
            val starter = Intent(context, VideoPlayActivity::class.java)
            starter.putExtra(EXTRA_VIDEO, data)
            context.startActivity(starter)
        }
    }
}
