package com.lcl6.cn.basedialog.mvp.presenter;

import com.lcl6.cn.basedialog.base.presnenter.BasePresenter;
import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.basedialog.mvp.model.NewsModel;
import com.lcl6.cn.basedialog.mvp.model.impl.NewsModelImpl;
import com.lcl6.cn.basedialog.mvp.view.NewsView;

import java.util.List;

/**
 * Created by liancl on 2017/8/23.
 */

public class NewsPresenter extends BasePresenter<NewsModel> {

    NewsModel mNewsModel;
    NewsView mNewsView;
    public NewsPresenter( NewsView newsView) {
        mNewsModel=new NewsModelImpl();
        mNewsView=newsView;
    }

    public void getData(){
        if (mNewsModel != null) {
            mNewsModel.getData(new NewsModel.LoadCompleteListener() {
                @Override
                public void comlpete(List<JsoupBean> list) {
                    mNewsView.showContent(list);
                }
            });
        }
    }

}
