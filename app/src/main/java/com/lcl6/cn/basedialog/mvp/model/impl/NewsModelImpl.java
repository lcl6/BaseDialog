package com.lcl6.cn.basedialog.mvp.model.impl;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.basedialog.mvp.model.NewsModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liancl on 2017/8/23.
 */

public class NewsModelImpl implements NewsModel {
     List<JsoupBean> mJsoupList = new ArrayList<>();
    @Override
    public void getData(final LoadCompleteListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Document document = Jsoup.connect("http://www.jianshu.com/")
                            .timeout(10000)
                            .get();
                    //获取根标签
                    Elements noteList = document.select("ul.note-list");
                    Elements li = noteList.select("li");
                    for (Element element : li) {
                        //获得tile
                        String title = element.select("a.title").text();
                        //头像+
                        String avatar = element.select("a.avatar").select("img").attr("src");
                        //详情链接
                        String attr = element.select("a.title").attr("abs:href");//过 attr("abs:href") 获取绝对路径。
                        Log.e(Constant.TAG, "title: "+title);
                        Log.e(Constant.TAG, "avatar: "+avatar);
                        Log.e(Constant.TAG, "attr: "+attr);
                        JsoupBean jsoupBean = new JsoupBean();
                        jsoupBean.setAttr(attr);
                        jsoupBean.setAvatar(avatar);
                        jsoupBean.setTitle(title);
                        mJsoupList.add(jsoupBean);
                    }
                    if(li.size()==mJsoupList.size()){
                        uihandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Message msg = new Message();
                                msg.what = 1;
                                msg.obj=listener;
                                uihandler.sendMessage(msg);
                            }
                        },1000);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



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
