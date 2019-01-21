package com.rjdeleon.tourista.feature.tripDialog;

import android.app.Application;
import android.os.AsyncTask;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

class TripDialogRepository {

    private final TripDao mDao;
    private final LiveData<Trip> mTrip;

    TripDialogRepository(@NonNull Application application, long id) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).tripDao();
        if (id == 0) {
            MutableLiveData<Trip> tripLd = new MutableLiveData<>();
            tripLd.setValue(new Trip());
            mTrip = tripLd;
        } else {
            mTrip = mDao.findById(id);
        }
    }

    LiveData<Trip> getTrip() {
        return mTrip;
    }

    void save() {
        new InsertAsyncTask(mDao).execute(mTrip.getValue());
    }

    private static class InsertAsyncTask extends AsyncTask<Trip, Void, Void> {

        private TripDao mDao;

        InsertAsyncTask(TripDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            Trip trip = trips[0];
            if (trip.getId() == 0) {
                mDao.insert(trip);
            } else {
                mDao.update(trip);
            }
            return null;
        }
    }
}
