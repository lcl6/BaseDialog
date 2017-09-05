package com.lcl6.cn.utils;

import android.os.Build;

/**
 * Created by liancl on 2017/9/5.
 */

public class SdkUtil {
    /**判断是否大于19*/
    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }
    /**判断是否大于21*/
    public static boolean hasLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
