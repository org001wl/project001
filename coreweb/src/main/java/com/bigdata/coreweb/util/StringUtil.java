package com.bigdata.coreweb.util;

public class StringUtil {
    public static boolean isNullOrEmpty(String msg){
        if(msg==null||msg.equals("")) return true;
        return false;
    }
    public static boolean equals(String str1,String str2){
        if(!isNullOrEmpty(str1)&&str1.equals(str2)) {
            return true;
        }
        if(isNullOrEmpty(str1)&&isNullOrEmpty(str2)){
            return true;
        }
        return false;
    }
}
