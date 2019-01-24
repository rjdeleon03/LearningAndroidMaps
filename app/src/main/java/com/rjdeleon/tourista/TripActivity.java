package com.rjdeleon.tourista;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.rjdeleon.tourista.core.calendar.CalendarUtils;
import com.rjdeleon.tourista.core.permissions.PermissionUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TripActivity extends AppCompatActivity {

    /* TODO: Temp account string constant */
    private static final String ACCOUNT = "account@gmail.com";

    @BindView(R.id.appToolbar)
    Toolbar appToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        ButterKnife.bind(this);
        setSupportActionBar(appToolbar);
        getWriteCalendarPermission();
    }

    private void getWriteCalendarPermission() {

        /* Get calendar read permission */
        PermissionUtils.getCalendarPermission(this);
    }

    private void saveCalendarId(long id) {
        SharedPreferences sp =
                getSharedPreferences(getString(R.string.sharedprefs_calendar), Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putLong(getString(R.string.sharedprefs_calendar_id), id);
        spEditor.apply();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (PermissionUtils.verifyCalendarPermissionResponse(requestCode, grantResults)) {

            long calendarId = CalendarUtils.createCalendar(this, ACCOUNT);
            saveCalendarId(calendarId);

        } else {

            Toast.makeText(this, R.string.error_calendar_permission, Toast.LENGTH_SHORT).show();

        }
    }
}
