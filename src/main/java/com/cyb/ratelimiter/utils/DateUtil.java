package com.cyb.ratelimiter.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间处理工具类
 * Created by ChenYueBo on 2016/7/2.
 */
public class DateUtil {


    /**
     * 将Date对象转换成指定格式的字符串
     * @param date
     * @param format
     * @return
     */
    public static String date2Str(Date date, String format){
        try {
            if(date != null && format != null) {
                DateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
                return dateFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将指定格式的字符串转成date对象
     * @param date
     * @param format
     * @return
     */
    public static Date str2Date(String date, String format){
        try {
            if(date != null || format != null){
                DateFormat dateFormat = new SimpleDateFormat(format, Locale.CHINA);
                return dateFormat.parse(date);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
