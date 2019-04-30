package com.linoer.app.utils;

public class StringUtils {
    public static boolean isNotNullAndNotEmpty(Object param){
        if(param == null){
            return false;
        }
        if(param instanceof Integer){
            return true;
        }
        return !param.equals("");
    }
}
