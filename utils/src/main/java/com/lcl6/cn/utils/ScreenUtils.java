package com.lcl6.cn.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by liancl on 2017/8/7.
 */

public class ScreenUtils {
    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * Adapt the screen for vertical slide.
     *
     * @param designWidthInDp The size of design diagram's width, in dp,
     *                        e.g. the design diagram width is 720px, in XHDPI device,
     *                        the designWidthInDp = 720 / 2.
     */
    public static void adaptScreen4VerticalSlide(Application app,final Activity activity,
                                                 final int designWidthInDp) {
        adaptScreen(app,activity, designWidthInDp, true);
    }

    /**
     * Adapt the screen for horizontal slide.
     *
     * @param designHeightInDp The size of design diagram's height, in dp,
     *                         e.g. the design diagram height is 1080px, in XXHDPI device,
     *                         the designHeightInDp = 1080 / 3.
     */
    public static void adaptScreen4HorizontalSlide(Application app,final Activity activity,
                                                   final int designHeightInDp) {
        adaptScreen(app,activity, designHeightInDp, false);
    }

    /**
     * Cancel adapt the screen.
     *
     * @param activity The activity.
     */
    public static void cancelAdaptScreen(final Application app,final Activity activity) {
        final DisplayMetrics appDm = app.getResources().getDisplayMetrics();
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();
        activityDm.density = appDm.density;
        activityDm.scaledDensity = appDm.scaledDensity;
        activityDm.densityDpi = appDm.densityDpi;
    }

    /**
     * Reference from: https://mp.weixin.qq.com/s/d9QCoBP6kV9VSWvVldVVwA
     */
    private static void adaptScreen(final Application app,final Activity activity,
                                    final float sizeInDp,
                                    final boolean isVerticalSlide) {
        final DisplayMetrics appDm = app.getResources().getDisplayMetrics();
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();
        if (isVerticalSlide) {
            activityDm.density = activityDm.widthPixels / sizeInDp;
        } else {
            activityDm.density = activityDm.heightPixels / sizeInDp;
        }
        activityDm.scaledDensity = activityDm.density * (appDm.scaledDensity / appDm.density);
        activityDm.densityDpi = (int) (160 * activityDm.density);
    }
}
