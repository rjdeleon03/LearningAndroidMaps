package com.rjdeleon.tourista.feature.timeZonePicker;

import android.app.Application;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.CustomTimeZone;
import com.rjdeleon.tourista.data.CustomTimeZoneDao;

import java.util.List;

import androidx.lifecycle.LiveData;

public class TimeZonePickerRepository {

    private CustomTimeZoneDao mDao;
    private final LiveData<List<CustomTimeZone>> mTimeZones;

    public TimeZonePickerRepository(Application application) {
        mDao = AppDatabase.getInstance(application.getApplicationContext())
                .customTimeZoneDao();
        mTimeZones = mDao.getAll();
    }

    public LiveData<List<CustomTimeZone>> getTimeZones() {
        return mTimeZones;
    }

    public LiveData<List<CustomTimeZone>> getTimeZonesByFilter(String filter) {
        return mDao.getByFilter("%" + filter + "%");
    }
}
