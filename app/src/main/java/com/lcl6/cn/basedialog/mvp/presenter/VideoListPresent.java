package com.lcl6.cn.basedialog.mvp.presenter;

import com.lcl6.cn.basedialog.api.Api;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.base.manager.observel.RxObservable;
import com.lcl6.cn.basedialog.bean.VideoInfo;
import com.lcl6.cn.basedialog.mvp.contract.VideoListConstract;
import com.lcl6.cn.basedialog.mvp.model.impl.VideoListModelImpl;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;

import static com.lcl6.cn.basedialog.api.Api.VIDEO_DOMAIN_NAME;

/**
 * Created by liancl on 2017/11/22.
 */

public class VideoListPresent extends RxPresenter<VideoListConstract.View> implements VideoListConstract.Present {
    @Inject
    VideoListModelImpl videoListModel;
    private VideoListConstract.View mView;

    public VideoListPresent(VideoListConstract.View view) {
        mView = view;
    }

    @Override
    public void getData() {

        //设置全局baseurl
//        App.getAppComponent().getRetrofitManager().setGlobalDomain(Api.NEWS_HOST);
        HttpUrl httpUrl =   App.getAppComponent().getRetrofitManager().fetchDomain(VIDEO_DOMAIN_NAME);
        if (httpUrl == null || !httpUrl.toString().equals(Api.NEWS_HOST)) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            App.getAppComponent().getRetrofitManager().putDomain(VIDEO_DOMAIN_NAME, Api.NEWS_HOST);
        }

        App.getAppComponent().getNetWorkManager().getRetrofit().create(Api.class).getVideoList("V9LG4B3A0",10)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(flatMapVideo("V9LG4B3A0"))
                .compose(mView.<List<VideoInfo>>bindToLife())
                .subscribe(new RxObservable<List<VideoInfo>>() {
                    @Override
                    protected void onRxComplete() {

                    }

                    @Override
                    protected void onRxNext(List<VideoInfo> value) {
                        mView.showContent(value);
                    }

                    @Override
                    protected void onRxSubscribe(Disposable d) {

                    }

                    @Override
                    protected void onRxError(Throwable e) {
                        mView.stateError();
                    }
                });
    }
    /**
     * 类型转换
     * @param typeStr 视频类型
     */
    private static Function<Map<String, List<VideoInfo>>, Observable<List<VideoInfo>>> flatMapVideo(final String typeStr) {
        return new Function<Map<String, List<VideoInfo>>, Observable<List<VideoInfo>>>() {
            @Override
            public Observable<List<VideoInfo>> apply(Map<String, List<VideoInfo>> stringListMap) throws Exception {
                return Observable.just(stringListMap.get(typeStr));
            }
        };
    }
}
