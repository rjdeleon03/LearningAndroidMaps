package com.rjdeleon.tourista.feature;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rjdeleon.tourista.data.AppDao;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.DbAsyncTask;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TopRepository {

    private AppDao mAppDao;
    private LiveData<Trip> mCachedTrip;
    private MutableLiveData<List<Destination>> mCachedDestinations;
    private String mId;

    TopRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAppDao = db.daoAccess();
    }

    void initialize(String id) {
        mId = id;
        mCachedTrip = mAppDao.getTrip(mId);

        if (mCachedTrip == null) {
            MutableLiveData<Trip> trip = new MutableLiveData<>();
            trip.setValue(new Trip());
            mCachedTrip = trip;
            mId = UUID.randomUUID().toString();
        }

        if (mCachedDestinations == null) {
            LiveData<List<Destination>> ldd = mAppDao.getDestinationsPerTrip(mId);
            MutableLiveData<List<Destination>> destinations = new MutableLiveData<>();
            if (ldd == null || ldd.getValue() == null) {
                destinations.setValue(new ArrayList<>());
                mCachedDestinations = destinations;
            } else {
                destinations.setValue(ldd.getValue());
            }
        }
    }

    void cleanUp() {
        mId = null;
        mCachedTrip = null;
        mCachedDestinations = null;
    }

    LiveData<Trip> getCachedTrip() {
        return mCachedTrip;
    }

    LiveData<List<Destination>> getCachedDestinations() {
        return mCachedDestinations;
    }

    void insertTrip(Trip trip) {

        if (mCachedDestinations.getValue() == null) return;

        trip.setId(UUID.randomUUID().toString());
        new InsertTripAsyncTask(mAppDao).execute(trip);
    }

    void insertDestination(Destination destination) {

        if (mCachedDestinations.getValue() == null) return;
        List<Destination> destinations = mCachedDestinations.getValue();
        destinations.add(destination);
        mCachedDestinations.setValue(destinations);
    }

    void deleteDestination(Destination destination) {

        if (mCachedDestinations.getValue() == null) return;
        List<Destination> destinations = mCachedDestinations.getValue();
        destinations.remove(destination);
        mCachedDestinations.setValue(destinations);
    }

    private static class InsertTripAsyncTask extends DbAsyncTask<Trip> {

        InsertTripAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            mAsyncAppDao.insertTrip(trips[0]);

            for(Destination destination : trips[0].getDestinations()) {
                destination.setTripId(trips[0].getId());
                new InsertDestinationAsyncTask(mAsyncAppDao).execute(destination);
            }
            return null;
        }
    }

    private static class InsertDestinationAsyncTask extends DbAsyncTask<Destination> {

        InsertDestinationAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mAsyncAppDao.insertDestination(destinations[0]);
            return null;
        }
    }
}
