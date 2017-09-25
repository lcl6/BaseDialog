package com.lcl6.cn.utils;

import android.util.TypedValue;

/**
 * Created by liancl on 2017/8/4.
 */

public class DensityUtils {

    /**
     * dp转px
     * @param dpValue dp值
     */
    public static int dp2px( float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     * @param spValue sp值
     */
    public static int sp2px( float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     * @param pxValue px值
     */
    public static float px2dp( float pxValue) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (pxValue / scale);
    }

    /**
     * px转sp
     * @param pxValue px值
     */
    public static float px2sp(float pxValue) {
        return (pxValue / Utils.getContext().getResources().getDisplayMetrics().scaledDensity);
    }
}
