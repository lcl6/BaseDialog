package com.lcl6.cn.utils;

import android.app.Application;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * 屏幕适配方案
 * Created by liancl on 2018/12/21.
 */
public class AdaptScreenUtils {

    private static boolean isInitMiui = false;
    private static Field mTmpMetrics;

    private static DisplayMetrics appDm;

    /**
     * Adapt for the horizontal screen, and call it in [android.app.Activity.getResources].
     */
    public static Resources adaptWidth(Application application,Resources resources, int designWidth) {
        DisplayMetrics dm = getDisplayMetrics(resources);
        float newXdpi = dm.xdpi = (dm.widthPixels * 72f) / designWidth;
        setAppDmXdpi(application,newXdpi);
        return resources;
    }

    /**
     * Adapt for the vertical screen, and call it in [android.app.Activity.getResources].
     */
    public static Resources adaptHeight(Application application,Resources resources, int designHeight) {
        DisplayMetrics dm = getDisplayMetrics(resources);
        float newXdpi = dm.xdpi = (dm.heightPixels * 72f) / designHeight;
        setAppDmXdpi(application,newXdpi);
        return resources;
    }

    /**
     * @param resources The resources.
     * @return the resource
     */
    public static Resources closeAdapt(Application application,Resources resources) {
        DisplayMetrics dm = getDisplayMetrics(resources);
        float newXdpi = dm.xdpi = dm.density * 72;
        setAppDmXdpi(application,newXdpi);
        return resources;
    }

    /**
     * Value of pt to value of px.
     *
     * @param ptValue The value of pt.
     * @return value of px
     */
    public static int pt2Px(Application application,float ptValue) {
        DisplayMetrics metrics =application.getResources().getDisplayMetrics();
        return (int) (ptValue * metrics.xdpi / 72f + 0.5);
    }

    /**
     * Value of px to value of pt.
     *
     * @param pxValue The value of px.
     * @return value of pt
     */
    public static int px2Pt(Application application,float pxValue) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        return (int) (pxValue * 72 / metrics.xdpi + 0.5);
    }

    private static void setAppDmXdpi(Application application,final float xdpi) {
        if (appDm == null) {
            appDm = application.getResources().getDisplayMetrics();
        }
        appDm.xdpi = xdpi;
    }

    private static DisplayMetrics getDisplayMetrics(Resources resources) {
        DisplayMetrics miuiDisplayMetrics = getMiuiTmpMetrics(resources);
        if (miuiDisplayMetrics == null) return resources.getDisplayMetrics();
        return miuiDisplayMetrics;
    }

    private static DisplayMetrics getMiuiTmpMetrics(Resources resources) {
        if (!isInitMiui) {
            DisplayMetrics ret = null;
            String simpleName = resources.getClass().getSimpleName();
            if ("MiuiResources".equals(simpleName) || "XResources".equals(simpleName)) {
                try {
                    mTmpMetrics = Resources.class.getDeclaredField("mTmpMetrics");
                    mTmpMetrics.setAccessible(true);
                    ret = (DisplayMetrics) mTmpMetrics.get(resources);
                } catch (Exception e) {
                    Log.e("AdaptScreenUtils", "no field of mTmpMetrics in resources.");
                }
            }
            isInitMiui = true;
            return ret;
        }
        if (mTmpMetrics == null) return null;
        try {
            return (DisplayMetrics) mTmpMetrics.get(resources);
        } catch (Exception e) {
            return null;
        }
    }
}
