package com.lcl6.cn.utils;

import android.app.Service;
import android.os.Vibrator;

/**
 * 手机震动帮助类
 * Created by liancl on 2017/6/19.
 */

public class VibratorUtil {

    /**
     * 震动手机
     * @param milliseconds 震动时间（毫秒）
     */
    public static Vibrator vibrate( long milliseconds) {
        Vibrator vibrator = (Vibrator) Utils.getContext().getSystemService(Service.VIBRATOR_SERVICE);
        if (vibrator != null){
            vibrator.vibrate(milliseconds);
        }
        return vibrator;
    }

    /**
     * 震动手机
     * @param pattern 自定义震动模式 数组中数字的含义依次是[静止时长，震动时长，静止时长，震动时长...] 时长的单位是毫秒
     * @param isRepeat 是否反复震动，如果是true，反复震动，如果是false，只震动一次
     */
    public static Vibrator vibrate(long[] pattern, boolean isRepeat) {
        Vibrator vibrator = (Vibrator) Utils.getContext().getSystemService(Service.VIBRATOR_SERVICE);
        if (vibrator != null){
            vibrator.vibrate(pattern, isRepeat ? 1 : -1);
        }
        return vibrator;
    }

    /**
     * 取消震动
     * @param vibrator 震动器
     */
    public static void cancel(Vibrator vibrator){
        if (vibrator != null){
            vibrator.cancel();
        }
    }

    /** 手机硬件是否有震动器 */
    public static boolean hasVibrator(){
        Vibrator vibrator = (Vibrator) Utils.getContext().getSystemService(Service.VIBRATOR_SERVICE);
        return vibrator != null && vibrator.hasVibrator();
    }
}
