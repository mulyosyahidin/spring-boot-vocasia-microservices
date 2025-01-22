package com.vocasia.payment.util;

public class StringUtil {
    public static String cutString(String str) {
        return StringUtil.cutString(str, 25);
    }

    public static String cutString(String str, int max) {
        if (str.length() > max) {
            return str.substring(0, max);
        } else {
            return str;
        }
    }
}
