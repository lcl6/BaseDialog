package com.lcl6.cn.basedialogl;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;

/**
 * 可扩展基类
 * Created by liancl on 2017/6/7.
 */

public abstract class BaseDialog extends Dialog {
    public static final String TAG = "BaseDialog";

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.BaseDialog);
        setContentView(getlayoutId());
        initView();
    }

    public BaseDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(getlayoutId());
        initView();
    }

    protected void initView() {
        findView();
        setListener();
        initData();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initParams(getWindow());
    }

    protected void initParams(Window window) {
    }

    @Override
    public void setContentView(@NonNull View view) {
        super.setContentView(view);
        initParams(getWindow());
    }

    /**
     * 初始化事件
     */
    protected void setListener() {
    }

    /**
     * 初始化控件
     */
    protected abstract void findView();

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    /**
     * 初始化布局
     */
    protected abstract int getlayoutId();
}
