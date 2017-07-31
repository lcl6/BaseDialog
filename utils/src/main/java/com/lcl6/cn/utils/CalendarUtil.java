package com.lcl6.cn.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by liancl on 2017/7/28.
 */

public class CalendarUtil {
    /**
     * 得到本周周一
     */
    public static String getMondayOfThisWeek() {
        Date date = MondayOfThisWeek();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }

    /**
     * 得到本周周一
     */
    public static Date MondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return c.getTime();
    }

    /**
     * 得到本周周日
     */
    public static String getSundayOfThisWeek() {
        Date date = SundayOfThisWeek();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }
    /**
     * 得到本周周日
     */
    public static Date SundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }

    /**
     * 得到上周周一
     */
    public static String getMondayOfLastWeek() {
        Date date = MondayOfLastWeek();
        return (String) DateFormat.format("yyy-MM-dd",date);
    }

    /**
     * 得到上周周一
     */
    public static Date MondayOfLastWeek() {
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.WEEK_OF_MONTH);
        c.set(Calendar.WEEK_OF_MONTH,i-1);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return c.getTime();
    }



    /**
     * 得到上周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfLastWeek() {
        Date date = SundayOfLastWeek();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }
    /**
     * 得到上周周日
     *
     * @return yyyy-MM-dd
     */
    public static Date SundayOfLastWeek() {
        Calendar c = Calendar.getInstance();
        int i = c.get(Calendar.WEEK_OF_MONTH);
        c.set(Calendar.WEEK_OF_MONTH,i-1);
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 7);
        return c.getTime();
    }

    /**
     * 得到本月一号
     */
    public static String getStartOfThisMouth() {
        Date date = StartOfThisMouth();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }
    /**
     * 得到本月一号
     */
    public static Date StartOfThisMouth() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return c.getTime();
    }



    /**
     * 得到本月最后一天
     */
    public static String getEndOfThisMouth() {
        Date date = EndOfThisMouth();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }
    /**
     * 得到本月最后一天
     */
    public static Date EndOfThisMouth() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        return ca.getTime();
    }

    /**
     * 得到上月一号
     */
    public static String getStartOfLastMouth() {
        Date date = StartOfLastMouth();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }
    /**
     * 得到上月一号
     */
    public static Date StartOfLastMouth() {
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        return cal_1.getTime();
    }



    /**
     * 得到上月最后一天
     */
    public static String getEndOfTLastMouth() {
        Date date = EndOfTLastMouth();
        return (String) DateFormat.format("yyy-MM-dd", date);
    }

    /**
     * 得到上月最后一天
     */
    public static Date EndOfTLastMouth() {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        return cale.getTime();
    }


    public static String getTimeByDate(Date date){
        return   (String) DateFormat.format("yyyyMMddHHmmssss", date);
    }
    public static String getTimeByStartDate(Date date){
      return   (String) DateFormat.format("yyyyMMdd", date)+"000000";
    }

    public static String getTimeByEndDate(Date date){
        return   (String) DateFormat.format("yyyyMMdd"+"235959", date);
    }


}
