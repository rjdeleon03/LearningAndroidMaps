package com.rjdeleon.tourista.feature.trip;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.rjdeleon.tourista.data.AppDao;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.DbAsyncTask;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;
import java.util.Objects;

public class TripRepository {

    private AppDao mAppDao;
    private LiveData<Trip> mTrip;
    private long mId;

    TripRepository(Application application, long id) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAppDao = db.daoAccess();
        mId = id;
        mTrip = mAppDao.getTrip(mId);

        if (mTrip == null || mTrip.getValue() == null) {
            MutableLiveData<Trip> trip = new MutableLiveData<>();
            trip.setValue(new Trip());
            mTrip = trip;
            return;
        }
        mTrip.getValue().setDestinations(getDestinationsPerTrip().getValue());
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    private LiveData<List<Destination>> getDestinationsPerTrip() {
        return mAppDao.getDestinationsPerTrip(mId);
    }

    void insert(Trip trip) {
        new InsertAsyncTask(mAppDao).execute(trip);
    }

    void delete() {
        new DeleteTripAsyncTask(mAppDao)
                .execute(Objects.requireNonNull(mTrip.getValue()).getId());
    }

    void deleteDestination(long id) {
        new DeleteDestinationAsyncTask(mAppDao).execute(id);
    }

    private static class InsertAsyncTask extends DbAsyncTask<Trip> {

        protected InsertAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Trip... trips) {
            mAsyncAppDao.insertTrip(trips[0]);
            return null;
        }
    }

    private static class DeleteTripAsyncTask extends DbAsyncTask<Long> {

        DeleteTripAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Long... nums) {
            mAsyncAppDao.deleteTrip(nums[0]);
            return null;
        }
    }

    private static class DeleteDestinationAsyncTask extends DbAsyncTask<Long> {

        DeleteDestinationAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Long... nums) {
            mAsyncAppDao.deleteDestination(nums[0]);
            return null;
        }
    }
}
