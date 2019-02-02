package com.rjdeleon.tourista.data;

import org.joda.time.DateTime;

import androidx.room.TypeConverter;

@SuppressWarnings("WeakerAccess")
class DateTimeConverters {

    @TypeConverter
    public static DateTime fromTimeStamp(long value) {
        return new DateTime(value);
    }


    @TypeConverter
    public static long dateToTimestamp(DateTime date) {
        return date.getMillis();
    }
}
