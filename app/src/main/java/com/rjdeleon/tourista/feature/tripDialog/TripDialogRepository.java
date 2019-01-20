package com.rjdeleon.tourista.feature.tripDialog;

import android.app.Application;
import android.os.AsyncTask;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

class TripDialogRepository {

    private final TripDao mDao;
    private final MutableLiveData<Trip> mTrip;

    TripDialogRepository(@NonNull Application application) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).tripDao();
        mTrip = new MediatorLiveData<>();
        mTrip.setValue(new Trip());
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
            mDao.insert(trips[0]);
            return null;
        }
    }
}
