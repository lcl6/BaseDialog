package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.adapter.mvp.MvpListAdapter;
import com.lcl6.cn.basedialog.bean.JsoupBean;
import com.lcl6.cn.basedialog.mvp.contract.NewsContract;
import com.lcl6.cn.basedialog.mvp.presenter.NewsPresenter;
import com.lcl6.cn.component.base.activity.BaseActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Created by liancl on 2017/8/23.
 */

public class MvpActivity  extends BaseActivity<NewsPresenter> implements NewsContract.View{
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    MvpListAdapter mAdapter;
    NewsPresenter mNewsPresenter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MvpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected NewsPresenter getPresenter() {
        mNewsPresenter= new NewsPresenter();
        return mNewsPresenter;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_mvp;
    }

    @Override
    protected void initData() {
        initPostData();
    }
    private void initPostData() {
        mNewsPresenter.getData();
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayout.VERTICAL);
        mRecyclerView.setLayoutManager(manager);
        mAdapter = new MvpListAdapter(getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showContent(List<JsoupBean> list) {
        mAdapter.setData(list);
        mAdapter.notifyDataSetChanged();
    }
}
