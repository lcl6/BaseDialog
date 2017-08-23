package com.lcl6.cn.basedialog.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by liancl on 2017/8/23.
 */

public abstract class BaseActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeCreatView();
        setContentView(setLayoutId());
        ButterKnife.bind(this);
        initView();
        initIntentData();
        initData();
        initViewListener();
    }
    protected abstract int setLayoutId();

    protected  void initIntentData(){};

    protected  void beforeCreatView(){ };

    protected  void initViewListener(){};

    protected abstract void initData();

    protected abstract void initView();


}
