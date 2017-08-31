package com.lcl6.cn.basedialog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lcl6.cn.basedialog.adapter.EdtextAdapter;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.di.bean.MyClassB;
import com.lcl6.cn.basedialog.di.component.DaggerActivityComponent;
import com.lcl6.cn.basedialog.di.model.ActivityModule;
import com.lcl6.cn.component.adapter.BaseRecyclerViewAdapter;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;
import com.lcl6.cn.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by liancl on 2017/8/30.
 */

public class DaggerScopActivity extends BaseActivity {

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
    protected RxPresenter getPresenter() {
        return null;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_dagger_list;
    }

    @Override
    protected void initView() {

        mlist=new ArrayList<>();

        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        mAdapter = new EdtextAdapter(getContext());
        mAdapter.setData(mlist);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        DaggerActivityComponent.builder().activityModule(new ActivityModule(2)).appComponent(App.getAppComponent()).build().inject(this);
        for (int i = 0; i < 20; i++) {
            mlist.add("你好");
        }
        mAdapter.notifyDataSetChanged();
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
}
