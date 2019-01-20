package com.rjdeleon.tourista.data;

import com.rjdeleon.tourista.Constants;

import org.joda.time.DateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import androidx.room.TypeConverter;

public class DateTimeConverters {

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
