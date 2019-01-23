package com.lcl6.cn.basedialog.mvp.model.impl

import android.util.Log
import com.lcl6.cn.basedialog.api.Api.GANK_DOMAIN_NAME
import com.lcl6.cn.basedialog.api.TwoApiService
import com.lcl6.cn.basedialog.app.App
import com.lcl6.cn.basedialog.base.manager.observel.RxObservable
import com.lcl6.cn.basedialog.bean.JsoupBean
import com.lcl6.cn.basedialog.constant.Constant
import com.lcl6.cn.basedialog.mvp.model.NewsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

/**
 * Created by liancl on 2017/8/23.
 */

class NewsModelImpl : NewsModel {
    internal var mJsoupList: MutableList<JsoupBean> = ArrayList()
    override fun getData(listener: NewsModel.LoadCompleteListener) {
        val retrofitManager = App.getAppComponent().retrofitManager
        val httpUrl2 = retrofitManager.fetchDomain(GANK_DOMAIN_NAME)
        if (httpUrl2 == null) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            retrofitManager.putDomain(GANK_DOMAIN_NAME, "http://gank.io")
        }
        App.getAppComponent().netWorkManager
                .retrofit.create(TwoApiService::class.java)
                .getData(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : RxObservable<ResponseBody>() {
                    override fun onRxComplete() {

                    }

                    override fun onRxSubscribe(d: Disposable) {

                    }

                    override fun onRxNext(response: ResponseBody) {
                        try {
                            val string = response.string()
                            val jsonObject = JSONObject(string)
                            val results = jsonObject.getJSONArray("results")
                            for (i in 0 until results.length()) {
                                val jsonObject1 = results.getJSONObject(i)
                                val desc = jsonObject1.getString("desc")
                                val source = jsonObject1.getString("source")
                                val jsoupBean = JsoupBean()
                                jsoupBean.title = desc
                                jsoupBean.attr = source
                                mJsoupList.add(jsoupBean)
                            }
                            listener.comlpete(mJsoupList)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }

                    }

                    override fun onRxError(e: Throwable) {
                        Log.e(Constant.TAG, "onError: ")
                    }
                })
    }
}
