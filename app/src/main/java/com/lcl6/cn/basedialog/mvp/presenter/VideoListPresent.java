package com.lcl6.cn.basedialog.mvp.presenter;

import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.mvp.contract.VideoListConstract;
import com.lcl6.cn.basedialog.mvp.model.VideoListModel;
import com.lcl6.cn.basedialog.mvp.model.impl.VideoListModelImpl;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import java.util.List;

/**
 * Created by liancl on 2017/11/22.
 */

public class VideoListPresent extends RxPresenter<VideoListConstract.View> implements VideoListConstract.Present {

    VideoListModelImpl videoListModel= new VideoListModelImpl();

    @Override
    public void getData() {
        videoListModel.getData(new VideoListModel.DataCallBack() {
            @Override
            public void sucess(List<VideoInfo> list) {
                if(mView!=null){
                    mView.showContent(list);
                }
            }

            @Override
            public void error(Throwable e) {
                if(mView!=null){
                    mView.stateError();
                }
            }
        });
    }
}
