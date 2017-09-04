package com.lcl6.cn.basedialog;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.lcl6.cn.basedialog.adapter.JsoupAdapter;
import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 测试网络爬虫
 * Created by liancl on 2017/8/17.
 */

public class JsoupActivity extends BaseActivity {

    public static final String TAG="result";
    List<JsoupBean> mJsoupList;
    @BindView(R.id.rv_jsoup)
    RecyclerView mJsoupRecyclerView;
    JsoupAdapter mJsoupAdapter;

    @Override
    protected int setLayoutId() {
        return R.layout.activity_jsoup;
    }

    @Override
    protected void initView() {
        Utils.init(getContext());
        mJsoupList = new ArrayList<>();
        initRecyclerView();
    }
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayout.VERTICAL);
        mJsoupRecyclerView.setLayoutManager(layoutManager);
        mJsoupAdapter= new JsoupAdapter(getContext());

        mJsoupRecyclerView.setAdapter(mJsoupAdapter);
    }

    private Handler uihandler= new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    onLoadSuccessStatus();
                    mJsoupAdapter.setData(mJsoupList);
                    mJsoupAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void initViewListener() {
        super.initViewListener();
        if(mJsoupAdapter!=null){
            mJsoupAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<JsoupBean>() {
                @Override
                public void onItemClick(RecyclerView.ViewHolder viewHolder, JsoupBean item, int position) {

                }
            });

        }
    }

    @Override
    protected void initData() {
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

                        Log.e(TAG, "title: "+title);
                        Log.e(TAG, "avatar: "+avatar);
                        Log.e(TAG, "attr: "+attr);
                        JsoupBean jsoupBean = new JsoupBean();
                        jsoupBean.setAttr(attr);
                        jsoupBean.setAvatar(avatar);
                        jsoupBean.setTitle(title);
                        mJsoupList.add(jsoupBean);
                    }
                    if(li.size()==mJsoupList.size()){
                        Message msg = new Message();
                        msg.what = 1;
                        uihandler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public static void start(Context context){
        Intent intent = new Intent();
        intent.setClass(context,JsoupActivity.class);
        context.startActivity(intent);
    }

}
