package com.rjdeleon.tourista.data;

import android.arch.persistence.room.TypeConverter;

import com.rjdeleon.tourista.Constants;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Converters {

    private static DateFormat df = new SimpleDateFormat(Constants.DATE_TIME_FORMAT, Locale.US);

    @TypeConverter
    public static DateTime fromTimeStamp(long value) {
        return new DateTime(value);
    }


    @TypeConverter
    public static long dateToTimestamp(DateTime date) {
        return date.getMillis();
    }
}
