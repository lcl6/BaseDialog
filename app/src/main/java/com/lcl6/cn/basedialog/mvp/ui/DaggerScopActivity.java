package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.adapter.EdtextAdapter;
import com.lcl6.cn.basedialog.di.bean.MyClassB;
import com.lcl6.cn.basedialog.mvp.contract.DaggerScopContract;
import com.lcl6.cn.basedialog.mvp.presenter.DaggerScopPresent;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;
import com.lcl6.cn.component.base.activity.BaseMvpActivity;
import com.lcl6.cn.component.widget.title.TitleBar;
import com.lcl6.cn.component.widget.title.TitleConfig;
import com.lcl6.cn.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by liancl on 2017/8/30.
 */

public class DaggerScopActivity extends BaseMvpActivity<DaggerScopPresent> implements DaggerScopContract.View{

    @Inject
    MyClassB classB;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    EdtextAdapter mAdapter;

    List<String> mlist;
    public static void start(Context context) {
        Intent starter = new Intent(context, DaggerScopActivity.class);
        context.startActivity(starter);
    }
    @Override
    protected DaggerScopPresent getPresenter() {
        DaggerScopPresent daggerScopPresent= new DaggerScopPresent();
        return daggerScopPresent;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_dagger_list;
    }

    @Override
    protected void initView() {
        TitleBar titleBar = getTitleBar();
        titleBar.removeAllActions(true);
        TitleConfig builde = getTitleConfigBuilder().setLeftVisible(false).builde();
        titleBar.setTitleConfig(builde);
        mlist=new ArrayList<>();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mAdapter = new EdtextAdapter(this);
        mAdapter.setData(mlist);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.getData();
    }

    @Override
    protected void initViewListener() {
        super.initViewListener();
        if(mAdapter!=null){
            mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {
                @Override
                public void onItemClick(RecyclerView.ViewHolder viewHolder, String item, int position) {
                    ToastUtils.showShort("点击了"+position);
                }
            });

        }
    }

    @Override
    public void showContent(List<String> list) {
        mlist.clear();
        mlist.addAll(list);
        mAdapter.notifyDataSetChanged();
        onLoadSuccessStatus();
    }

}
