package com.lcl6.cn.basedialog.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.app.App;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.utils.AdaptScreenUtils;

/**
 * Created by liancl on 2018/12/21.
 */
public class AdapterActivity extends BaseActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, AdapterActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_adapter;
    }

    @Override
    protected void initView() {

        onLoadSuccessStatus();
    }

    @Override
    protected void initData() {
    }

    @Override
    public Resources getResources() {
//        return AdaptScreenUtils.closeAdapt(App.getInstance(),super.getResources());
        return AdaptScreenUtils.adaptWidth(App.getInstance(),super.getResources(),1080);
    }
}
