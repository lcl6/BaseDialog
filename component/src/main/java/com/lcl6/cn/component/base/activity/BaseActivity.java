package com.lcl6.cn.component.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.lcl6.cn.component.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 无mvp的基类
 * Created by liancl on 2017/9/1.
 */

public abstract class BaseActivity extends RxAppCompatActivity {
    Unbinder mUnbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        mUnbinder = ButterKnife.bind(this);
        initView();
        initIntentData();
        initData();
        initViewListener();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = getLayoutInflater().inflate(R.layout.activity_base, null);
        super.setContentView(view);
        FrameLayout frameLayout = (FrameLayout)view.findViewById(R.id.fl_contain);
        View childView = LayoutInflater.from(this).inflate(layoutResID, null);
        frameLayout.addView(childView,0);
    }

    public Context getContext() {
        return this;
    }

    protected abstract int setLayoutId();

    protected void beforeCreatView() {
    }

    protected abstract void initView();

    protected void initIntentData() {
    }

    protected abstract void initData();

    protected void initViewListener() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }
}
