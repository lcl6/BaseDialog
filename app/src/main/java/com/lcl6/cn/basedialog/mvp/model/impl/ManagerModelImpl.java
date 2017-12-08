package com.lcl6.cn.basedialog.mvp.model.impl;

import android.util.Log;

import com.lcl6.cn.basedialog.api.OneApiService;
import com.lcl6.cn.basedialog.api.ThreeApiService;
import com.lcl6.cn.basedialog.api.TwoApiService;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.di.component.AppComponent;
import com.lcl6.cn.basedialog.mvp.model.ManagerModel;
import com.lcl6.cn.component.net.RetrofitManager;
import com.lcl6.cn.component.net.util.TransformerUtil;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

import static com.lcl6.cn.basedialog.api.Api.DOUBAN_DOMAIN_NAME;
import static com.lcl6.cn.basedialog.api.Api.GANK_DOMAIN_NAME;
import static com.lcl6.cn.basedialog.api.Api.GITHUB_DOMAIN_NAME;

/**
 * Created by liancl on 2017/9/1.
 */

public class ManagerModelImpl implements ManagerModel {
    @Inject
    public ManagerModelImpl() {
    }

    @Override
    public void requestTabaoData(LoadCompleteListener listener) {

        App.getAppComponent().getNetWorkManager().getRetrofit().create(OneApiService.class)
                .requestDefault()
                .compose(TransformerUtil.<ResponseBody>getDefaultTransformer())
                .subscribe(getDefaultObserver(listener));
    }

    @Override
    public void setGlobal(LoadCompleteListener listener,String newUrl) {
        //当你项目中只有一个 BaseUrl ,但需要动态改变,全局 BaseUrl 显得非常方便
        RetrofitManager retrofitManager = App.getAppComponent().getRetrofitManager();
        HttpUrl httpUrl =  retrofitManager.getGlobalDomain();
        if (null == httpUrl || !httpUrl.toString().equals(newUrl)){
            retrofitManager.setGlobalDomain(newUrl);
        }
        listener.onComplete("全局替换baseUrl成功");

    }

    @Override
    public void removeGlobal(LoadCompleteListener listener) {
        //不想再使用全局 BaseUrl ,想用之前传入 Retrofit 的默认 BaseUrl ,就Remove
        App.getAppComponent().getRetrofitManager().removeGlobalDomain();
        listener.onComplete("移除了全局baseUrl");
    }

    @Override
    public void requestGuthub(LoadCompleteListener listener,String newUrl) {
        HttpUrl httpUrl =   App.getAppComponent().getRetrofitManager().fetchDomain(GITHUB_DOMAIN_NAME);
        if (httpUrl == null || !httpUrl.toString().equals(newUrl)) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            App.getAppComponent().getRetrofitManager().putDomain(GITHUB_DOMAIN_NAME, newUrl);
        }
        App.getAppComponent().getNetWorkManager()
                .getRetrofit().create(OneApiService.class)
                .getUsers(1, 10)
                .compose(TransformerUtil.<ResponseBody>getDefaultTransformer())
                .subscribe(getDefaultObserver(listener));
    }

    @Override
    public void requestGank(LoadCompleteListener listener,String newUrl) {
        AppComponent appComponent = App.getAppComponent();
        HttpUrl httpUrl2 =  appComponent.getRetrofitManager().fetchDomain(GANK_DOMAIN_NAME);
        if (httpUrl2 == null || !httpUrl2.toString().equals(newUrl)){ //可以在 App 运行时随意切换某个接口的 BaseUrl
            appComponent.getRetrofitManager().putDomain(GANK_DOMAIN_NAME, newUrl);
        }
        appComponent.getNetWorkManager()
                .getRetrofit().create(TwoApiService.class)
                .getData(10, 1)
                .compose(TransformerUtil.<ResponseBody>getDefaultTransformer())
                .subscribe(getDefaultObserver(listener));
    }

    @Override
    public void requestDouban(LoadCompleteListener listener,String newUrl) {
        AppComponent mAppComponent = App.getAppComponent();
        HttpUrl httpUrl3 =  mAppComponent.getRetrofitManager().fetchDomain(DOUBAN_DOMAIN_NAME);
        if (httpUrl3 == null || !httpUrl3.toString().equals(newUrl)) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            mAppComponent.getRetrofitManager().putDomain(DOUBAN_DOMAIN_NAME, newUrl);
        }
        mAppComponent.getNetWorkManager()
                .getRetrofit().create(ThreeApiService.class)
                .getBook(1220562)
                .compose(TransformerUtil.<ResponseBody>getDefaultTransformer())
                .subscribe(getDefaultObserver(listener));
    }


    private Observer<ResponseBody> getDefaultObserver(final LoadCompleteListener listener) {
        return new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody response) {
                try {
                    String string = response.string();
                    Log.d("test", string);
                    listener.onComplete(string);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
                listener.onFail(throwable.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
    }

}
