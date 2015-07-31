package com.example.mingdao.mingdao;

import android.content.Context;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by mingdao on 15/7/30.
 */
public class DataUtilJudge {


    public static long DataUtilJudge(String get) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        Date gettime = new Date(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            gettime = format.parse(get);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long diff = curDate.getTime()-gettime.getTime();
        return diff/1000;
    }
}
