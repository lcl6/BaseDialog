package com.lcl6.cn.basedialogl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.lcl6.cn.basedialogl.utils.ScreenUtils;

/**
 * 右进右出的dialog
 * Created by liancl on 2017/6/8.
 */

public abstract class BaseRightDialog extends BaseDialog {
    public BaseRightDialog(@NonNull Context context) {
        super(context);
        initWindowAnim();
    }

    protected  void initWindowAnim(){
        if(getWindow()!=null){
            getWindow().setWindowAnimations(R.style.animation_right_in_right_out);
        }
    }

    public BaseRightDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initWindowAnim();
    }

    @Override
    protected void initParams(Window window) {
        if(window!=null){
            window.setGravity(Gravity.RIGHT|Gravity.CENTER_VERTICAL);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.height= ScreenUtils.getScreenHeight(getContext());
            window.setAttributes(layoutParams);

        }
    }
}
