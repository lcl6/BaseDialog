package com.lcl6.cn.utils;

import android.support.annotation.StringDef;
import android.text.format.DateFormat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * Created by liancl on 2017/6/12.
 */

public class DateUtils {


    @StringDef({FormatType.Type_1, FormatType.Type_2, FormatType.Type_3, FormatType.Type_4, FormatType.Type_5, FormatType.Type_6, FormatType.Type_7})
    @Retention(RetentionPolicy.SOURCE)
    public @interface FormatType {
        String Type_1 = "HH:mm";
        String Type_2 = "yyyy-MM-dd HH:mm:ss";
        String Type_3 = "yyyyMMddHHmmssSSS";
        String Type_4 = "yyyyMMddHHmmss";
        String Type_5 = "yyyyMMdd";
        String Type_6 = "yyyy-MM-dd";
        String Type_7 = "yyyy-MM-dd-HH-mm-ss";
    }

    /**
     * 格式化当前时间
     * @param formatType 格式化类型
     */
    public static String getCurrentFormatString(String formatType) {
        return getFormatString(formatType, new Date(System.currentTimeMillis()));
    }

    /**
     * 格式化当前时间
     * @param formatType 格式化类型
     * @param date 日期
     */
    public static String getFormatString(String formatType, Date date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatType, Locale.CHINA);
            return format.format(date);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 将格式化后的时间转成Date
     * @param formatType source对应的格式化类型
     * @param source 时间数据
     */
    public static Date parseFormatDate(String formatType, String source) {
        SimpleDateFormat format = new SimpleDateFormat(formatType, Locale.CHINA);
        try {
            return format.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 改变日期格式
     * @param oldFormatType 旧格式
     * @param newFormatType 新格式
     * @param source 时间数据
     */
    public static String changeFormatString(String oldFormatType, String newFormatType, String source) {
        Date date = parseFormatDate(oldFormatType, source);
        if (date == null){
            return "";
        }
        return getFormatString(newFormatType, date);
    }

    /**
     * 根据日历获取对应格式的时间
     * @param formatType 时间格式
     * @param calendar 日历
     */
    public static String parseFormatCalendar(String formatType, Calendar calendar){
        try {
            return DateFormat.format(formatType, calendar).toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getBefore(int type){
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
        Date dateNow = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateNow);
        //calendar .add(Calendar.DAY_OF_YEAR, -1); //一天
        //calendar .add(Calendar.WEEK_OF_YEAR, -1); //一周
        calendar.add(Calendar.MONTH, -1); // 一个月
        Date dateFrom = calendar.getTime();
        String beforeTime=format.format(dateFrom);
        return beforeTime;
    }


}
