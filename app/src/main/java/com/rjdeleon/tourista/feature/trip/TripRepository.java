package com.rjdeleon.tourista.feature.trip;

import android.app.Application;
import android.arch.lifecycle.LiveData;

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

    public TripRepository(Application application, long id) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAppDao = db.daoAccess();
        mId = id;
        mTrip = mAppDao.getTrip(mId);

        Objects.requireNonNull(mTrip.getValue())
                .setDestinations(getDestinationsPerTrip().getValue());
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    public LiveData<List<Destination>> getDestinationsPerTrip() {
        return mAppDao.getDestinationsPerTrip(mId);
    }

    public void delete() {
        new DeleteTripAsyncTask(mAppDao)
                .execute(Objects.requireNonNull(mTrip.getValue()).getId());
    }

    public void deleteDestination(long id) {
        new DeleteDestinationAsyncTask(mAppDao).execute(id);
    }

    private static class DeleteTripAsyncTask extends DbAsyncTask<Long> {

        protected DeleteTripAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Long... nums) {
            mAsyncAppDao.deleteTrip(nums[0]);
            return null;
        }
    }

    private static class DeleteDestinationAsyncTask extends DbAsyncTask<Long> {

        protected DeleteDestinationAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Long... nums) {
            mAsyncAppDao.deleteDestination(nums[0]);
            return null;
        }
    }
}
