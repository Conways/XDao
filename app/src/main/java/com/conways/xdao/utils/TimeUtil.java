package com.conways.xdao.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        return getDataByType(time,"yyyy-MM-dd HH:mm");
    }

    /**
     *
     * @param time
     * @param format
     * @return
     */
    public static String getDataByType(long time, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date(time));
    }


}
