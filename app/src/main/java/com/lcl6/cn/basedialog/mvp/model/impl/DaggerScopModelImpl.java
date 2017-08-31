package com.lcl6.cn.basedialog.mvp.model.impl;

import android.os.Handler;

import com.lcl6.cn.basedialog.mvp.model.DaggerScopModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liancl on 2017/8/31.
 */

public class DaggerScopModelImpl implements DaggerScopModel {

    List<String> mList = new ArrayList<>();

    @Override
    public void getData(final LoadCompleteListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 20; i++) {
                    mList.add("你I好");
                }
                listener.onComplete(mList);
            }
        },1000);

//        HttpUrl httpUrl2 = RetrofitManager.getInstance().fetchDomain(GANK_DOMAIN_NAME);
//        if (httpUrl2 == null) { //可以在 App 运行时随意切换某个接口的 BaseUrl
//            RetrofitManager.getInstance().putDomain(GANK_DOMAIN_NAME, "http://gank.io");
//        }
//        NetWorkManager.getInstance()
//                .getTwoApiService()
//                .getData(10, 1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new RxObservable<ResponseBody>() {
//                    @Override
//                    protected void onRxComplete() {
//
//                    }
//
//                    @Override
//                    protected void onRxNext(ResponseBody response) {
//                        try {
//                            String string = response.string();
//                            JSONObject jsonObject = new JSONObject(string);
//                            JSONArray results = jsonObject.getJSONArray("results");
//                            for (int i = 0; i < results.length(); i++) {
//                                JSONObject jsonObject1 = results.getJSONObject(i);
//                                String desc = jsonObject1.getString("desc");
//                                String source = jsonObject1.getString("source");
//                                JsoupBean jsoupBean = new JsoupBean();
//                                jsoupBean.setTitle(desc);
//                                jsoupBean.setAttr(source);
//                                mList.add(desc);
//                            }
//                            listener.onComplete(mList);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    protected void onRxSubscribe(Disposable d) {
//
//                    }
//                    @Override
//                    protected void onRxError(Throwable e) {
//                        Log.e(Constant.TAG, "onError: " );
//                    }
//                });

    }
}
