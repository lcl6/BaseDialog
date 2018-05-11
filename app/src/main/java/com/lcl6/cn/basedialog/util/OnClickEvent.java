package com.lcl6.cn.basedialog.util;

import android.view.View;

/**
 * 判断是不是点击多次
 * Created by liancl on 2018/5/11.
 */

public abstract  class OnClickEvent implements View.OnClickListener{
    public static long lastTime;

    public abstract void singleClick(View v);

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    public boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;

        if (time < 1000) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}
