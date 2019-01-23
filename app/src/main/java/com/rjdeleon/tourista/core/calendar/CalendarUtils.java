package com.rjdeleon.tourista.core.calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

public class CalendarUtils {

    private static final String ACCOUNT_TYPE = "com.rjdeleon.tourista";
    private static final String NAME = "tourista_calendar";
    private static final String DISPLAY_NAME = "Tourista Calendar";

    public static void createCalendar(Activity activity, String account) {

        // TODO: Check if calendar exists

        ContentValues cv = new ContentValues();
        cv.put(CalendarContract.Calendars.ACCOUNT_NAME, account);
        cv.put(CalendarContract.Calendars.ACCOUNT_TYPE, ACCOUNT_TYPE);
        cv.put(CalendarContract.Calendars.NAME, NAME);
        cv.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, DISPLAY_NAME);
        cv.put(CalendarContract.Calendars.CALENDAR_COLOR, "232323");
        cv.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.OWNER_ACCOUNT);
        cv.put(CalendarContract.Calendars.ALLOWED_REMINDERS, "METHOD_ALERT, METHOD_ALARM");
        cv.put(CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES, "TYPE_OPTIONAL, TYPE_REQUIRED, TYPE_RESOURCE");
        cv.put(CalendarContract.Calendars.ALLOWED_AVAILABILITY, "AVAILABILITY_BUSY, AVAILABILITY_FREE, AVAILABILITY_TENTATIVE");

        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        uri = uri.buildUpon().appendQueryParameter(android.provider.CalendarContract.CALLER_IS_SYNCADAPTER,"true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, account)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, account).build();
        activity.getContentResolver().insert(uri, cv);
    }

    private static boolean verifyCalendarExists(Activity activity, String account) {

        Cursor cursor = null;
        ContentResolver cr = activity.getContentResolver();

        String[] projection = {
                CalendarContract.Calendars.ALLOWED_ATTENDEE_TYPES,
                CalendarContract.Calendars.ACCOUNT_NAME,
                CalendarContract.Calendars.NAME,
                CalendarContract.Calendars.CALENDAR_LOCATION,
                CalendarContract.Calendars.CALENDAR_TIME_ZONE
        };

        Uri uri = CalendarContract.Calendars.CONTENT_URI;
        String selection = "((" + CalendarContract.Calendars.ACCOUNT_NAME + " = ?) AND ("
                + CalendarContract.Calendars.ACCOUNT_TYPE + " = ?) AND ("
                + CalendarContract.Calendars.OWNER_ACCOUNT + " = ?))";
        String[] selectionArgs = new String[]{account, ACCOUNT_TYPE,
                account};

        cursor = cr.query(uri, projection, selection, selectionArgs, null);

        if (cursor == null)
            return false;

        while (cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.NAME)).equals(NAME))
                return true;
        }

        return false;
    }

    public static void create() {}

    private static void readEvent() {}

    private static void updateEvent() {}

    private static void deleteEvent() {}
}
