package com.rjdeleon.tourista.feature.triplist;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.rjdeleon.tourista.data.AppDao;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.DbAsyncTask;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TripListRepository {

    private AppDao mAppDao;
    private LiveData<List<Trip>> mTrips;

    public TripListRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAppDao = db.daoAccess();
        mTrips = mAppDao.getAllTrips();
    }

    public LiveData<List<Trip>> getTrips() {
        return mTrips;
    }

    public void insert(Trip trip) {
        new InsertAsyncTask(mAppDao).execute(trip);
    }

    public void delete(String id) {
        new DeleteAsyncTask(mAppDao).execute(id);
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

    private static class DeleteAsyncTask extends DbAsyncTask<String> {

        protected DeleteAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(String... nums) {
            mAsyncAppDao.deleteTrip(nums[0]);
            return null;
        }
    }
}
