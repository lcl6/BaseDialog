package com.lcl6.cn.basedialog.mvp.ui;

import android.content.Context;
import android.content.Intent;

import com.lcl6.cn.basedialog.R;
import com.lcl6.cn.basedialog.base.BaseActivity;

/**
 * Created by liancl on 2017/8/23.
 */

public class MvpActivity  extends BaseActivity{
    public static void start(Context context) {
        Intent starter = new Intent(context, MvpActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_main;
    }
}
