package com.rjdeleon.tourista;

import android.os.Bundle;
import android.widget.Toast;

import com.rjdeleon.tourista.core.calendar.CalendarUtils;
import com.rjdeleon.tourista.core.permissions.PermissionUtils;
import com.rjdeleon.tourista.core.sharedprefs.SharedPrefsUtils;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TripActivity extends AppCompatActivity {

    /* TODO: Temp account string constant */
    private static final String ACCOUNT = "account@gmail.com";

    private NavController mNavController;

    @BindView(R.id.appToolbar)
    Toolbar appToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        ButterKnife.bind(this);
        setSupportActionBar(appToolbar);

        mNavController = Navigation.findNavController(this, R.id.navigation_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController);
        setupCalendar();

        mNavController = Navigation.findNavController(this, R.id.navigation_fragment);
    }

    private void setupCalendar() {

        /* Check if calendar ID exists in shared prefs */
        long calendarId = SharedPrefsUtils.getCalendarId(this);
        if (calendarId == 0) {
            PermissionUtils.getCalendarPermission(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (PermissionUtils.verifyCalendarPermissionResponse(requestCode, grantResults)) {

            long calendarId = CalendarUtils.createCalendar(this, ACCOUNT);
            SharedPrefsUtils.saveCalendarId(this, calendarId);

        } else {

            Toast.makeText(this, R.string.error_calendar_permission, Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        mNavController.navigateUp();
        return true;
    }
}
