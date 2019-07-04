package org.elastos.record.utility.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 */
public class DateUtils {
    private DateUtils() {

    }

    protected static ThreadLocal<SimpleDateFormat> defaultDateFormat = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    protected static ThreadLocal<SimpleDateFormat> yyyyMMddHHmmssSSS = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyyMMddHHmmssSSS");
        }
    };

    public static String formatDate() {
        return defaultDateFormat.get().format(now());
    }

    public static String formatYYYYMMddHHmmssSSS() {
        return yyyyMMddHHmmssSSS.get().format(now());
    }

    public static String formatDate(Date date) {
        return defaultDateFormat.get().format(date);
    }

    public static String formatDate(Date date, String pattern) {
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static Long unixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public static Long unixTime(Date date) {
        return date.getTime() / 1000L;
    }

    public static Date parseDateString(String time) {

        try {
            return defaultDateFormat.get().parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDateFromUnixTime(Long time) {
        return time==null?null:new Date(time *1000L);
    }
}
