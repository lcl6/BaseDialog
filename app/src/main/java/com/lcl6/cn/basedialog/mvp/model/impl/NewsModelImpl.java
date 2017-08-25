package com.lcl6.cn.basedialog.mvp.model.impl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lcl6.cn.basedialog.base.manager.NetWorkManager;
import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.basedialog.mvp.model.NewsModel;
import com.lcl6.cn.component.net.RetrofitManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

import static com.lcl6.cn.basedialog.api.Api.GANK_DOMAIN_NAME;
import static com.lcl6.cn.basedialogl.BaseDialog.TAG;

/**
 * Created by liancl on 2017/8/23.
 */

public class NewsModelImpl implements NewsModel {
     List<JsoupBean> mJsoupList = new ArrayList<>();
    @Override
    public void getData(final LoadCompleteListener listener) {
        HttpUrl httpUrl2 = RetrofitManager.getInstance().fetchDomain(GANK_DOMAIN_NAME);
        if (httpUrl2 == null) { //可以在 App 运行时随意切换某个接口的 BaseUrl
            RetrofitManager.getInstance().putDomain(GANK_DOMAIN_NAME, "http://gank.io");
        }
        NetWorkManager.getInstance().getTwoApiService()
                .getData(10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody response) {
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
                    public void onError(Throwable e) {

                        Log.e(TAG, "onError: " );
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Document document = Jsoup.connect("http://www.jianshu.com/")
//                            .timeout(10000)
//                            .get();
//                    //获取根标签
//                    Elements noteList = document.select("ul.note-list");
//                    Elements li = noteList.select("li");
//                    for (Element element : li) {
//                        //获得tile
//                        String title = element.select("a.title").text();
//                        //头像+
//                        String avatar = element.select("a.avatar").select("img").attr("src");
//                        //详情链接
//                        String attr = element.select("a.title").attr("abs:href");//过 attr("abs:href") 获取绝对路径。
//                        Log.e(Constant.TAG, "title: "+title);
//                        Log.e(Constant.TAG, "avatar: "+avatar);
//                        Log.e(Constant.TAG, "attr: "+attr);
//                        JsoupBean jsoupBean = new JsoupBean();
//                        jsoupBean.setAttr(attr);
//                        jsoupBean.setAvatar(avatar);
//                        jsoupBean.setTitle(title);
//                        mJsoupList.add(jsoupBean);
//                    }
//                    if(li.size()==mJsoupList.size()){
//                        uihandler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                Message msg = new Message();
//                                msg.what = 1;
//                                msg.obj=listener;
//                                uihandler.sendMessage(msg);
//                            }
//                        },10);
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();



    }



    private Handler uihandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LoadCompleteListener listener = (LoadCompleteListener) msg.obj;
            switch (msg.what){
                case 1:
                    listener.comlpete(mJsoupList);
                    break;
            }
        }
    };
}
