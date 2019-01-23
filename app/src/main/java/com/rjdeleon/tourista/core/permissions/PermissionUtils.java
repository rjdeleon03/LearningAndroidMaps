package com.rjdeleon.tourista.core.permissions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class PermissionUtils {

    private static final int REQ_CODE_CALENDAR = 1001;
    private static final int REQ_CODE_READ_CALENDAR = 1002;

    public static void getCalendarPermission(Activity activity) {
        if (isCalendarPermissionGranted(activity)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_CALENDAR}, REQ_CODE_CALENDAR);
        }
    }

    public static boolean verifyCalendarPermissionResponse(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQ_CODE_CALENDAR) {
            return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public static boolean isCalendarPermissionGranted(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED;
    }

    public static void getReadCalendarPermission(Activity activity) {
        if (isReadCalendarPermissionGranted(activity)) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_CALENDAR}, REQ_CODE_READ_CALENDAR);
        }
    }

    public static boolean verifyReadCalendarPermissionResponse(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQ_CODE_READ_CALENDAR) {
            return grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
        return false;
    }

    public static boolean isReadCalendarPermissionGranted(Context context) {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED;
    }
}
