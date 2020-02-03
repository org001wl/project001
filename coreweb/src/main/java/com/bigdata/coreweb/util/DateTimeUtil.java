package com.bigdata.coreweb.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("/yyyy/MM/dd/");
    /**
     * 获取当前时间
     *
     * @return
     */
    public static Timestamp now() {
        Date date = new Date();
        return new Timestamp(date.getTime());
    }

    public static Long nowLong() {
        Date date = new Date();
        return date.getTime();
    }

    /**
     * 生成日志格式的目录
     * @return
     */
    public static String buildDatePath() {
        LocalDateTime now = LocalDateTime.now();
        String nowText = now.format(formatter);
        return nowText;
    }
}
