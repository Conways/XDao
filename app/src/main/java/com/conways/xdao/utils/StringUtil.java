package com.conways.xdao.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by John on 2016/9/20.
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return null == str || "".equals(str.trim());
    }


    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

}
