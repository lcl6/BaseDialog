package com.lcl6.cn.basedialog.mvp.presenter

import com.lcl6.cn.basedialog.bean.JsoupBean
import com.lcl6.cn.basedialog.mvp.contract.NewsContract
import com.lcl6.cn.basedialog.mvp.model.NewsModel
import com.lcl6.cn.basedialog.mvp.model.impl.NewsModelImpl
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter

import javax.inject.Inject

/**
 * Created by liancl on 2017/8/23.
 */

class NewsPresenter @Inject
constructor() : RxPresenter<NewsContract.View>(), NewsContract.Presenter {

    @Inject
    internal var mNewsModel: NewsModelImpl? = null

    override fun getData() {
        if (mNewsModel != null) {
            mNewsModel!!.getData(object : NewsModel.LoadCompleteListener {
                override fun comlpete(list: List<JsoupBean>) {
                    mView.showContent(list)
                }
            })
        }
    }
}
