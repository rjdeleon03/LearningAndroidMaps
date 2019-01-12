package com.rjdeleon.tourista.feature.tripDialog;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

class TripDialogRepository {

    private TripDao mDao;
    private MutableLiveData<Trip> mTrip;

    TripDialogRepository(@NonNull Application application) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).tripDao();
        mTrip = new MediatorLiveData<>();
        mTrip.setValue(new Trip());
    }

    LiveData<Trip> getTrip() {
        return mTrip;
    }

    void save()
    {
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
