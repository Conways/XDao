package com.conways.xdao.utils;

import java.text.SimpleDateFormat;

/**
 * Created by John on 2016/9/19.
 */
public class TimeUtil {
    /**
     *
     * @param time
     * @return
     */
    public static String getDataOfTypeOne(long time) {
        return getDataByType(time,"yyyy-mm-dd hh:mm");
    }

    /**
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDataByType(long time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(time);
    }


}
