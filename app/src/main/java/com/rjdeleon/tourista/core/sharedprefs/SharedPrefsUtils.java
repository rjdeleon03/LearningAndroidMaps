package com.rjdeleon.tourista.core.sharedprefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.rjdeleon.tourista.R;

public class SharedPrefsUtils {

    public static long getCalendarId(Context context) {
        SharedPreferences sp =
                context.getSharedPreferences(
                        context.getString(R.string.sharedprefs_calendar), Context.MODE_PRIVATE);
        long id = sp.getLong(context.getString(R.string.sharedprefs_calendar_id), 0);
        return id;
    }

    public static void saveCalendarId(Context context, long id) {
        SharedPreferences sp =
                context.getSharedPreferences(
                        context.getString(R.string.sharedprefs_calendar), Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putLong(context.getString(R.string.sharedprefs_calendar_id), id);
        spEditor.apply();
    }
}
