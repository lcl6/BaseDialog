package com.lcl6.cn.basedialog.mvp.presenter;

import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.basedialog.mvp.contract.NewsContract;
import com.lcl6.cn.basedialog.mvp.model.NewsModel;
import com.lcl6.cn.basedialog.mvp.model.impl.NewsModelImpl;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import java.util.List;

/**
 * Created by liancl on 2017/8/23.
 */

public class NewsPresenter extends RxPresenter<NewsContract.View> implements NewsContract.Presenter {

    NewsModel mNewsModel;
    public NewsPresenter() {
        mNewsModel=new NewsModelImpl();
    }

    @Override
    public void getData() {
        if (mNewsModel != null) {
            mNewsModel.getData(new NewsModel.LoadCompleteListener() {
                @Override
                public void comlpete(List<JsoupBean> list) {
                    mView.showContent(list);
                }
            });
        }
    }
}
