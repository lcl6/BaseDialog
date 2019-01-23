package com.lcl6.cn.basedialog.mvp.ui

import android.content.Context
import android.content.Intent
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.lcl6.cn.basedialog.R
import com.lcl6.cn.basedialog.app.App
import com.lcl6.cn.basedialog.di.component.AppComponent
import com.lcl6.cn.basedialog.mvp.contract.ManagerContract
import com.lcl6.cn.basedialog.mvp.presenter.ManagerPresenter
import com.lcl6.cn.component.base.activity.BaseMvpActivity
import com.lcl6.cn.component.net.UrlChangeListener
import com.lcl6.cn.utils.ToastUtils
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.HttpUrl

/**
 * Created by liancl on 2017/8/25.
 */

class ManagerActivity : BaseMvpActivity<ManagerPresenter>(), ManagerContract.View {

    @BindView(R.id.et_url1)
    internal var mUrl1: EditText? = null
    @BindView(R.id.et_url2)
    internal var mUrl2: EditText? = null
    @BindView(R.id.et_url3)
    internal var mUrl3: EditText? = null
    @BindView(R.id.et_global_url)
    internal var mGlobalUrl: EditText? = null


    private var mListener: ChangeListener? = null
    internal lateinit var mAppComponent: AppComponent

    internal lateinit var managerPresenter: ManagerPresenter

    override fun getPresenter(): ManagerPresenter {
        managerPresenter = ManagerPresenter()
        return managerPresenter
    }

    override fun setLayoutId(): Int {
        return R.layout.activity_manager
    }

    override fun initView() {
        ButterKnife.bind(this)
        mUrl1!!.setSelection(mUrl1!!.text.toString().length)
        mAppComponent = App.getAppComponent()
    }

    override fun initData() {
        onLoadSuccessStatus()
    }

    override fun initViewListener() {
        super.initViewListener()
        initListener()
    }

    private fun initListener() {
        this.mListener = ChangeListener()
    }

    @OnClick(R.id.bt_request1, R.id.bt_request2, R.id.bt_request3)
    fun onClick(v: View) {
        when (v.id) {
            R.id.bt_request1 -> managerPresenter.requestGuthub(mUrl1!!.text.toString())
            R.id.bt_request2 -> managerPresenter.requestGank(mUrl2!!.text.toString())
            R.id.bt_request3 -> managerPresenter.requestDouban(mUrl3!!.text.toString())
        }
    }

    // 请求默认 BaseUrl，请求的接口没有配置 DomainHeader，所以只受全局 BaseUrl的影响
    fun btnRequestDefault(view: View) {
        managerPresenter.requestTabaoData()
    }

    // 设置全局替换的 BaseUrl
    fun btnSetGlobalUrl(view: View) {
        managerPresenter.setGlobal(mGlobalUrl!!.text.toString().trim { it <= ' ' })
    }

    // 移除全局的 BaseUrl
    fun btnRmoveGlobalUrl(view: View) {
        managerPresenter.removeGlobal()
    }

    private fun showResult(result: String) {
        AlertDialog.Builder(this)
                .setMessage(result)
                .setCancelable(true)
                .setPositiveButton("ok") { dialog, which -> dialog.dismiss() }
                .setNegativeButton("cancel") { dialog, which -> dialog.dismiss() }
                .create()
                .show()
    }

    override fun requestTabaoData(message: String) {
        showResult(message)
    }

    override fun setGlobal(message: String) {
        ToastUtils.showShort(message)
    }

    override fun removeGlobal(message: String) {
        ToastUtils.showShort(message)
    }

    override fun requestGuthub(message: String) {
        showResult(message)
    }

    override fun requestGank(message: String) {
        showResult(message)
    }

    override fun requestDouban(message: String) {
        showResult(message)
    }


    internal inner class ChangeListener : UrlChangeListener {

        override fun onUrlChange(newUrl: HttpUrl, oldUrl: HttpUrl) {
            Observable.just(1)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ Toast.makeText(applicationContext, "The newUrl is { " + newUrl.toString() + " }", Toast.LENGTH_LONG).show() }, { throwable -> throwable.printStackTrace() })
        }
    }

    companion object {

        fun start(context: Context) {
            val starter = Intent(context, ManagerActivity::class.java)
            context.startActivity(starter)
        }
    }
}
