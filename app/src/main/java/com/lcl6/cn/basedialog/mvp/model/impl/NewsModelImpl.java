package com.lcl6.cn.basedialog.mvp.model.impl;

import android.util.Log;

import com.lcl6.cn.basedialog.api.TwoApiService;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.base.manager.observel.RxObservable;
import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.basedialog.mvp.model.NewsModel;
import com.lcl6.cn.component.net.RetrofitManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

import static com.lcl6.cn.basedialog.api.Api.GANK_DOMAIN_NAME;

/**
 * Created by liancl on 2017/8/23.
 */

public class NewsModelImpl implements NewsModel {
    List<JsoupBean> mJsoupList = new ArrayList<>();
    @Override
    public void getData(final LoadCompleteListener listener) {
        RetrofitManager retrofitManager = App.getAppComponent().getRetrofitManager();
        HttpUrl httpUrl2 = retrofitManager.fetchDomain(GANK_DOMAIN_NAME);
        if (httpUrl2 == null) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            retrofitManager.putDomain(GANK_DOMAIN_NAME, "http://gank.io");
        }
        App.getAppComponent().getNetWorkManager()
                .getRetrofit().create(TwoApiService.class)
                .getData(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxObservable<ResponseBody>() {
                    @Override
                    protected void onRxComplete() {

                    }

                    @Override
                    protected void onRxSubscribe(Disposable d) {

                    }

                    @Override
                    protected void onRxNext(ResponseBody response) {
                        try {
                            String string = response.string();
                            JSONObject jsonObject = new JSONObject(string);
                            JSONArray results = jsonObject.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject jsonObject1 = results.getJSONObject(i);
                                String desc = jsonObject1.getString("desc");
                                String source = jsonObject1.getString("source");
                                JsoupBean jsoupBean = new JsoupBean();
                                jsoupBean.setTitle(desc);
                                jsoupBean.setAttr(source);
                                mJsoupList.add(jsoupBean);
                            }
                            listener.comlpete(mJsoupList);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    protected void onRxError(Throwable e) {
                        Log.e(Constant.TAG, "onError: " );
                    }
                });
    }
}
