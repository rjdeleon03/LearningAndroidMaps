package com.rjdeleon.tourista.data;

import android.annotation.SuppressLint;
import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter {

    @SuppressLint("SimpleDateFormat")
    private static DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    @TypeConverter
    public static Date fromTimeStamp(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @TypeConverter
    public static String dateToTimestamp(Date date) {
        if (date != null) {
            return df.format(date);
        }
        return null;
    }
}
