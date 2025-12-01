package com.bite.springblogdemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(date);
        // https://docs.oracle.com/javase/8/docs/api/  -> SimpleDateFormat
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateFormat = format.format(date);
        System.out.println(dateFormat);
    }
}
