package com.rjdeleon.tourista.feature.tripIndiv;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

public class TripIndivRepository {

    private final TripDao mDao;
    private final LiveData<Trip> mTrip;

    TripIndivRepository(@NonNull Application application, long id) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).tripDao();
        mTrip = mDao.findById(id);
    }

    LiveData<Trip> getTrip() {
        return mTrip;
    }

    void save() {
        new UpdateAsyncTask(mDao).execute(mTrip.getValue());
    }

    private static class UpdateAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mDao;

        UpdateAsyncTask(TripDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            mDao.update(trips[0]);
            return null;
        }
    }
}
