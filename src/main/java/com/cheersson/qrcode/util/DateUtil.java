package com.cheersson.qrcode.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author cheguangai
 * @date 2019/3/15 0015
 */
public class DateUtil {
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 返回年周
     * @param date
     * @param sundayAsFirstDay
     * @return
     */
    public static int getYearWeek(Date date, boolean sundayAsFirstDay){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setFirstDayOfWeek(sundayAsFirstDay ? Calendar.SATURDAY : Calendar.MONDAY);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static String format(Date date, String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String date, String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
