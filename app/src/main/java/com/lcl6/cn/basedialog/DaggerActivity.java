package com.lcl6.cn.basedialog;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lcl6.cn.basedialog.constant.Constant;
import com.lcl6.cn.basedialog.di.bean.ClassA;
import com.lcl6.cn.basedialog.di.component.DaggerClassAComponent;
import com.lcl6.cn.basedialog.di.model.ModuleA;
import com.lcl6.cn.component.base.activity.BaseActivity;
import com.lcl6.cn.component.base.mvp.presnenter.RxPresenter;

import javax.inject.Inject;

/**
 * Created by liancl on 2017/8/29.
 */

public class DaggerActivity extends BaseActivity {


    @Inject
    ClassA classA;

    public static void start(Context context) {
        Intent starter = new Intent(context, DaggerActivity.class);
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
        DaggerClassAComponent.builder()
                .moduleA(new ModuleA(2,3))
                .build()
                .inject(this);
    }
    @Override
    protected void initData() {
        int a = classA.getA();
        int b = classA.getB();

        Log.e(Constant.TAG, "initData:a "+a );
        Log.e(Constant.TAG, "initData:b "+b);

    }


}
