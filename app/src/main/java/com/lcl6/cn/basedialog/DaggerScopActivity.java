package com.lcl6.cn.basedialog;

import android.content.Context;
import android.content.Intent;

import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.basedialog.di.bean.MyClassB;
import com.lcl6.cn.basedialog.di.component.DaggerActivityComponent;
import com.lcl6.cn.basedialog.di.model.ActivityModule;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import javax.inject.Inject;

/**
 * Created by liancl on 2017/8/30.
 */

public class DaggerScopActivity extends BaseActivity {

    @Inject
    MyClassB classB;

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
        return R.layout.activity_dagger;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        DaggerActivityComponent.builder().activityModule(new ActivityModule(2)).appComponent(App.getAppComponent()).build().inject(this);
    }



}
