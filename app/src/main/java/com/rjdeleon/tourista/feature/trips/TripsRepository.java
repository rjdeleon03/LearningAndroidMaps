package com.rjdeleon.tourista.feature.trips;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

import java.util.List;

public class TripsRepository {

    private TripDao mDao;
    private final LiveData<List<Trip>> mTrips;

    public TripsRepository(@NonNull Application application) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).tripDao();
        mTrips = mDao.getAll();
    }

    public LiveData<List<Trip>> getTrips() {
        return mTrips;
    }
}