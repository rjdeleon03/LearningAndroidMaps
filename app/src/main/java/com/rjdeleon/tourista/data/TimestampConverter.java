package com.rjdeleon.tourista.data;

import android.arch.persistence.room.TypeConverter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampConverter {

    static DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");

    @TypeConverter
    public static Date fromTimestamp(String value) {
        if (value != null) {
            try {
                return df.parse(value);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return null;
        }
    }

    @TypeConverter
    public static String dateToTimestamp(Date date) {
        if (date != null) {
            return df.format(date);
        }
        return null;
    }
}
