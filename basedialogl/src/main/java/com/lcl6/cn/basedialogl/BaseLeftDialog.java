package com.lcl6.cn.basedialogl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.lcl6.cn.basedialogl.utils.ScreenUtils;

/**
 * 左进左出的dialog
 * Created by liancl on 2017/6/8.
 */

public abstract class BaseLeftDialog extends BaseDialog {
    public BaseLeftDialog(@NonNull Context context) {
        super(context);
        initWindowAnim();
    }

    protected  void initWindowAnim(){
        if(getWindow()!=null){
            getWindow().setWindowAnimations(R.style.animation_left_in_left_out);
        }
    }

    public BaseLeftDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initWindowAnim();
    }

    @Override
    protected void initParams(Window window) {
        if(window!=null){
            window.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.height= ScreenUtils.getScreenHeight(getContext());
            window.setAttributes(layoutParams);

        }
    }
}
