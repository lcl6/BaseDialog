package com.lcl6.cn.component.widget;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.util.Calendar;

/**
 * Created by liancl on 2017/9/3
 */

public class PickDateTimeDialog {
    public static void pickDate(Context context, DatePickerDialog.OnDateSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        Dialog dialog = new DatePickerDialog(context,listener ,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    public static void pickTime(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        Dialog dialog = new TimePickerDialog(context, listener,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), true);
        dialog.show();
    }



    /** 获得月*/
    public static String getMounthOfYear(int monthofyear){
        if(monthofyear<9){
            return "0"+(monthofyear+1);
        }
        return monthofyear+1+"";

    }
    /** 获得日*/
    public static String getDayOfMonth(int dayOfMonth){
        if(dayOfMonth<9){
            return "0"+(dayOfMonth);
        }
        return dayOfMonth+"";

    }

    /** 获得小时和分钟*/
    public static String getHourOrMinute(int hourOrMinute){
        if(hourOrMinute<10){
            return "0"+hourOrMinute;
        }
        return hourOrMinute+"";

    }
}
