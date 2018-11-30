package com.rjdeleon.tourista.feature.destination;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.rjdeleon.tourista.data.AppDao;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Destination;

import java.util.List;

public class DestinationRepository {

    private AppDao mAppDao;
    private LiveData<List<Destination>> mDestinations;
    private long mTripId;

    public DestinationRepository(Application application,long tripId) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAppDao = db.daoAccess();
        mTripId = tripId;
        mDestinations = mAppDao.getDestinationsPerTrip(mTripId);
    }

    public LiveData<List<Destination>> getDestinations() {
        return mDestinations;
    }

    public void insert(Destination destination) {
        new InsertAsyncTask(mAppDao).execute(destination);
    }

    public void update(Destination destination) {
        new UpdateAsyncTask(mAppDao).execute(destination);
    }

    private static class InsertAsyncTask extends AsyncTask<Destination, Void, Void> {

        private AppDao mAsyncAppDao;

        public InsertAsyncTask(AppDao appDao) {
            mAsyncAppDao = appDao;
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mAsyncAppDao.insertDestination(destinations[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Destination, Void, Void> {

        private AppDao mAsyncAppDao;

        public UpdateAsyncTask(AppDao appDao) {
            mAsyncAppDao = appDao;
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mAsyncAppDao.updateDestination(destinations[0]);
            return null;
        }
    }
}
