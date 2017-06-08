package com.lcl6.cn.basedialogl;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.lcl6.cn.basedialogl.utils.ScreenUtils;

/**
 * 底部弹出的dialog
 * Created by liancl on 2017/6/8.
 */

public abstract class BaseBottomDialog extends BaseDialog {
    public BaseBottomDialog(@NonNull Context context) {
        super(context);
        setWindowAnimations();
    }
    public BaseBottomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setWindowAnimations();
    }
    protected  void setWindowAnimations(){
        if (getWindow() != null){
            getWindow().setWindowAnimations(R.style.window_bottom_enter_out_doalog); //设置窗口弹出动画
        }
    }

    /**
     * 设置显示位置
     */
    @Override
    protected void initParams(Window window) {
        if(window!=null){
            window.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL);
            WindowManager.LayoutParams layoutParams=window.getAttributes();
            layoutParams.width= ScreenUtils.getScreenWidth(getContext());
            window.setAttributes(layoutParams);
        }

    }
}
