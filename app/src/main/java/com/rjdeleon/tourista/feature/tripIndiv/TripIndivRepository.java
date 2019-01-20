package com.rjdeleon.tourista.feature.tripIndiv;

import android.app.Application;
import android.os.AsyncTask;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class TripIndivRepository {

    private final TripDao mTripDao;
    private final LiveData<Trip> mTrip;
    private final LiveData<List<Destination>> mDestinations;

    TripIndivRepository(@NonNull Application application, long id) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        mTripDao = db.tripDao();
        mTrip = mTripDao.findById(id);
        mDestinations = db.destinationDao().findByTripId(id);
    }

    LiveData<Trip> getTrip() {
        return mTrip;
    }

    LiveData<List<Destination>> getDestinations() {
        return mDestinations;
    }

    void save() {
        new UpdateAsyncTask(mTripDao).execute(mTrip.getValue());
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
