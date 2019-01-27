package com.rjdeleon.tourista;

import android.app.Application;
import android.os.AsyncTask;

import com.rjdeleon.tourista.core.common.AppExecutors;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.CustomTimeZone;
import com.rjdeleon.tourista.data.CustomTimeZoneDao;

import java.util.TimeZone;

public class TouristaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupTimeZones();
    }

    private void setupTimeZones() {
        AppDatabase appDb = AppDatabase.getInstance(this);
        CustomTimeZoneDao dao = appDb.customTimeZoneDao();

        AppExecutors.getInstance().getDiskIO().execute(() -> {
            if (dao.getCount() == 0) {
                for(String id : TimeZone.getAvailableIDs()) {
                    TimeZone tz = TimeZone.getTimeZone(id);
                    dao.insert(new CustomTimeZone(id, tz.getDisplayName()));
                }
            }
        });
    }
}
