package com.rjdeleon.tourista.old.tripIndiv;

import android.app.Application;
import android.os.AsyncTask;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.DestinationDao;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.data.TripDao;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

class TripIndivRepository {

    private final TripDao mTripDao;
    private final DestinationDao mDestinationDao;
    private final LiveData<Trip> mTrip;
    private final LiveData<List<Destination>> mDestinations;

    TripIndivRepository(@NonNull Application application, long id) {
        AppDatabase db = AppDatabase.getInstance(application.getApplicationContext());
        mTripDao = db.tripDao();
        mTrip = mTripDao.findById(id);
        mDestinationDao = db.destinationDao();
        mDestinations = mDestinationDao.findByTripId(id);
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

    void deleteDestination(int index) {
        new DeleteAsyncTask(mDestinationDao)
                .execute(Objects.requireNonNull(mDestinations.getValue()).get(index));
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

    private static class DeleteAsyncTask extends AsyncTask<Destination, Void, Void> {

        private DestinationDao mDao;

        DeleteAsyncTask(DestinationDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mDao.delete(destinations[0]);
            return null;
        }
    }
}
