package com.lcl6.cn.utils.time;

import android.os.CountDownTimer;

/**
 * 倒计时工具类
 * Created by liancl on 2017/9/28.
 */

public class CountDownTimerUtil {

    /**
     *获得定时器
     * 需要调用 timer.start() 才开始倒计时
     * @param time 总时间
     * @param intervalTime 间隔时间
     * @param listener 回调
     */
    public static CountDownTimer getTimer(long time,long intervalTime,final Listener listener){
        CountDownTimer timer = new CountDownTimer(time+500, intervalTime){
            @Override
            public void onTick(long millisUntilFinished) {
                listener.onTick(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                listener.onFinish();
            }
        };
        return timer;
    }

    /**
     * 销毁定时器 防止内存泄漏
     */
    public static void destoryTimer(CountDownTimer timer ){
        if (timer == null) {
            return;
        }
        timer.cancel();
    }


   public interface Listener{
       void onTick(long millisUntilFinished);
       void onFinish();
   }


}
