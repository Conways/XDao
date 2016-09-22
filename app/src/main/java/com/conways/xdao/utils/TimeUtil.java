package com.conways.xdao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by John on 2016/9/19.
 */
public class TimeUtil {
    /**
     * @param time
     * @return
     */
    public static String getDataOfTypeOne(long time) {
        return getDataByType(time, "yyyy-MM-dd HH:mm");
    }

    /**
     * @param time
     * @param format
     * @return
     */
    public static String getDataByType(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }

    /**
     * 获取昨天凌晨的时间戳
     *
     * @return
     */
    public static long getYesterday() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.setTimeInMillis(System.currentTimeMillis()-24*60*60*100);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }

    /**
     * 获取今天的时间戳
     *
     * @return
     */
    public static long getToday() {

        Calendar todayStart = Calendar.getInstance();
        todayStart.setTimeInMillis(System.currentTimeMillis());
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }


}
