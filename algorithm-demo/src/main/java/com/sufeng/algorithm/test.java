package com.sufeng.algorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author xiekangkang
 * @date 2020/9/27 11:23
 */
public class test {
    public static void main(String[] args) {
        long a = getLongTimeMillisFromYMDStr("2020-09-22");
        long b = System.currentTimeMillis();
        System.out.println(a);
        System.out.println(b);
        System.out.println(getDayStartTimeSecond(a));
        System.out.println(getDayStartTimeSecond(b));
        System.out.println((b - a) / 60 / 60);
    }

    public static Long getLongTimeMillisFromYMDStr(String ymdStr){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        try {
            return simpleDateFormat.parse(ymdStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1L;
    }

    public static long getDayStartTimeSecond(long timeMillis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(timeMillis);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis() / 1000L;
    }
}
