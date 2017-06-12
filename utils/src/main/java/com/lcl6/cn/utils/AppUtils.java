package com.lcl6.cn.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

/**
 *
 * Created by liancl on 2017/6/12.
 */

public class AppUtils {
    /**
     * 是否处于栈顶
     */
    public static boolean isMainActivityTop(Activity activity){
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        String name = manager.getRunningTasks(1).get(0).topActivity.getClassName();
        return name.equals(activity.getLocalClassName());
    }

}
