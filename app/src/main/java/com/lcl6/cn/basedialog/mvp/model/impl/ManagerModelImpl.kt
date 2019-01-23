package com.lcl6.cn.basedialog.mvp.model.impl

import android.util.Log
import com.lcl6.cn.basedialog.api.Api.*
import com.lcl6.cn.basedialog.api.OneApiService
import com.lcl6.cn.basedialog.api.ThreeApiService
import com.lcl6.cn.basedialog.api.TwoApiService
import com.lcl6.cn.basedialog.app.App
import com.lcl6.cn.basedialog.mvp.model.ManagerModel
import com.lcl6.cn.component.net.util.TransformerUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.io.IOException
import javax.inject.Inject

/**
 * Created by liancl on 2017/9/1.
 */

class ManagerModelImpl @Inject
constructor() : ManagerModel {

    override fun requestTabaoData(listener: ManagerModel.LoadCompleteListener) {

        App.getAppComponent().netWorkManager.retrofit.create(OneApiService::class.java)
                .requestDefault()
                .compose(TransformerUtil.getDefaultTransformer())
                .subscribe(getDefaultObserver(listener))
    }

    override fun setGlobal(listener: ManagerModel.LoadCompleteListener, newUrl: String) {
        //当你项目中只有一个 BaseUrl ,但需要动态改变,全局 BaseUrl 显得非常方便
        val retrofitManager = App.getAppComponent().retrofitManager
        val httpUrl = retrofitManager.globalDomain
        if (null == httpUrl || httpUrl.toString() != newUrl) {
            retrofitManager.setGlobalDomain(newUrl)
        }
        listener.onComplete("全局替换baseUrl成功")

    }

    override fun removeGlobal(listener: ManagerModel.LoadCompleteListener) {
        //不想再使用全局 BaseUrl ,想用之前传入 Retrofit 的默认 BaseUrl ,就Remove
        App.getAppComponent().retrofitManager.removeGlobalDomain()
        listener.onComplete("移除了全局baseUrl")
    }

    override fun requestGuthub(listener: ManagerModel.LoadCompleteListener, newUrl: String) {
        val httpUrl = App.getAppComponent().retrofitManager.fetchDomain(GITHUB_DOMAIN_NAME)
        if (httpUrl == null || httpUrl.toString() != newUrl) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            App.getAppComponent().retrofitManager.putDomain(GITHUB_DOMAIN_NAME, newUrl)
        }
        App.getAppComponent().netWorkManager
                .retrofit.create(OneApiService::class.java)
                .getUsers(1, 10)
                .compose(TransformerUtil.getDefaultTransformer())
                .subscribe(getDefaultObserver(listener))
    }

    override fun requestGank(listener: ManagerModel.LoadCompleteListener, newUrl: String) {
        val appComponent = App.getAppComponent()
        val httpUrl2 = appComponent.retrofitManager.fetchDomain(GANK_DOMAIN_NAME)
        if (httpUrl2 == null || httpUrl2.toString() != newUrl) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            appComponent.retrofitManager.putDomain(GANK_DOMAIN_NAME, newUrl)
        }
        appComponent.netWorkManager
                .retrofit.create(TwoApiService::class.java)
                .getData(10, 1)
                .compose(TransformerUtil.getDefaultTransformer())
                .subscribe(getDefaultObserver(listener))
    }

    override fun requestDouban(listener: ManagerModel.LoadCompleteListener, newUrl: String) {
        val mAppComponent = App.getAppComponent()
        val httpUrl3 = mAppComponent.retrofitManager.fetchDomain(DOUBAN_DOMAIN_NAME)
        if (httpUrl3 == null || httpUrl3.toString() != newUrl) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            mAppComponent.retrofitManager.putDomain(DOUBAN_DOMAIN_NAME, newUrl)
        }
        mAppComponent.netWorkManager
                .retrofit.create(ThreeApiService::class.java)
                .getBook(1220562)
                .compose(TransformerUtil.getDefaultTransformer())
                .subscribe(getDefaultObserver(listener))
    }


    private fun getDefaultObserver(listener: ManagerModel.LoadCompleteListener): Observer<ResponseBody> {
        return object : Observer<ResponseBody> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(response: ResponseBody) {
                try {
                    val string = response.string()
                    Log.d("test", string)
                    listener.onComplete(string)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }

            override fun onError(throwable: Throwable) {
                throwable.printStackTrace()
                listener.onFail(throwable.message)
            }

            override fun onComplete() {

            }
        }
    }

}
