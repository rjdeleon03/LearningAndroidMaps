package com.rjdeleon.tourista.core.calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CalendarContract;

import com.rjdeleon.tourista.data.Destination;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarUtils {

    private static final String ACCOUNT_TYPE = "com.rjdeleon.tourista";
    private static final String NAME = "tourista_calendar";
    private static final String DISPLAY_NAME = "Tourista Calendar";
    private static final String EVENT_REMINDER_MINS = "30";

    public static long createCalendar(Activity activity, String account) {

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
        Uri calendarUri = activity.getContentResolver().insert(uri, cv);
        return ContentUris.parseId(calendarUri);
    }

    @SuppressLint("MissingPermission")
    public static boolean verifyCalendarExists(Activity activity, String account) {

        Cursor cursor = null;
        ContentResolver cr = activity.getContentResolver();

        String[] projection = {
                CalendarContract.Calendars._ID,
                CalendarContract.Calendars.NAME
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
            if (cursor.getString(cursor.getColumnIndex(CalendarContract.Calendars.NAME)).equals(NAME)){
                cursor.close();
                return true;
            }
            cursor.close();
        }

        return false;
    }

    public static long createEvent(Context context, Destination destination, long calendarId) {

        ContentResolver cr = context.getContentResolver();
        ContentValues cv = new ContentValues();

        cv.put(CalendarContract.Events.DTSTART, destination.getStartTime().getMillis());
        cv.put(CalendarContract.Events.DTEND, destination.getEndTime().getMillis());
        cv.put(CalendarContract.Events.TITLE, destination.getName());
        cv.put(CalendarContract.Events.DESCRIPTION, destination.getNotes());
        cv.put(CalendarContract.Events.CALENDAR_ID, calendarId);
        cv.put(CalendarContract.Events.EVENT_LOCATION, destination.getName());
        cv.put(CalendarContract.Events.EVENT_TIMEZONE, destination.getTimeZone());

        Uri eventUri = cr.insert(CalendarContract.Events.CONTENT_URI, cv);
        long eventId = ContentUris.parseId(eventUri);
        createReminder(context, eventId);
        return eventId;
    }

    private static void readEvent() {}

    public static int updateEvent(Context context, Destination destination, long calendarId) {

        ContentResolver cr = context.getContentResolver();
        ContentValues cv = new ContentValues();

        cv.put(CalendarContract.Events.DTSTART, destination.getStartTime().getMillis());
        cv.put(CalendarContract.Events.DTEND, destination.getEndTime().getMillis());
        cv.put(CalendarContract.Events.TITLE, destination.getName());
        cv.put(CalendarContract.Events.DESCRIPTION, destination.getNotes());
        cv.put(CalendarContract.Events.CALENDAR_ID, calendarId);
        cv.put(CalendarContract.Events.EVENT_LOCATION, destination.getName());
        cv.put(CalendarContract.Events.EVENT_TIMEZONE, destination.getTimeZone());

        String selectionClause = CalendarContract.Events.TITLE + " = ?";
        String[] selectionArgs = {String.valueOf(destination.getEventId())};

        Uri uri = CalendarContract.Events.CONTENT_URI;
        return cr.update(uri, cv, selectionClause, selectionArgs);
    }

    private static int deleteEvent(Context context, Destination destination) {

        ContentResolver cr = context.getContentResolver();

        String selectionClause = CalendarContract.Events.TITLE + " = ?";
        String[] selectionArgs = {String.valueOf(destination.getEventId())};

        Uri uri = CalendarContract.Events.CONTENT_URI;
        return cr.delete(uri, selectionClause, selectionArgs);
    }

    private static void createReminder(Context context, long eventId) {

        ContentResolver cr = context.getContentResolver();
        ContentValues cv = new ContentValues();
        cv.put(CalendarContract.Reminders.EVENT_ID, String.valueOf(eventId));
        cv.put(CalendarContract.Reminders.MINUTES, EVENT_REMINDER_MINS);
        cv.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        cr.insert(CalendarContract.Reminders.CONTENT_URI, cv);
    }
}
