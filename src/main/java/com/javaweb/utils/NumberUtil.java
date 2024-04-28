package com.javaweb.utils;

public class NumberUtil {
    public static boolean isNumber(String val) {
        try {
            Long number=Long.parseLong(val);
            return true;
        }
        catch(NumberFormatException ex) {
            return false;
        }
    }
}
