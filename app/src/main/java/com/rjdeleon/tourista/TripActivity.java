package com.rjdeleon.tourista;

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

    @BindView(R.id.appToolbar)
    Toolbar appToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        ButterKnife.bind(this);
        setSupportActionBar(appToolbar);
        setupCalendar();
    }

    private void setupCalendar() {
        PermissionUtils.getCalendarPermission(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (PermissionUtils.verifyCalendarPermissionResponse(requestCode, grantResults)) {
            CalendarUtils.createCalendar(this, "account@gmail.com");
        } else {
            Toast.makeText(this, R.string.error_calendar_permission, Toast.LENGTH_SHORT).show();
        }
    }
}
