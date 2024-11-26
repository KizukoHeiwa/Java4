package com.org.java4.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class XDate {
    static SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        }
        catch (ParseException ex) {
            return null;
        }
    }

    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }

    public static boolean isDate(String date) {
        return date.matches("\\d{1,2}/\\d{1,2}/\\d{4}");
    }

}
