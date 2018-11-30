package com.rjdeleon.tourista.feature.destination;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.rjdeleon.tourista.data.AppDao;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.DbAsyncTask;
import com.rjdeleon.tourista.data.Destination;

public class DestinationRepository {

    private AppDao mAppDao;
    private LiveData<Destination> mDestination;
    private long mId;

    public DestinationRepository(Application application, long id) {
        AppDatabase db = AppDatabase.getDatabase(application.getApplicationContext());
        mAppDao = db.daoAccess();
        mId = id;
        mDestination = mAppDao.getDestination(mId);
    }

    public LiveData<Destination> getDestination() {
        return mDestination;
    }

    public void insert(Destination destination) {
        new InsertAsyncTask(mAppDao).execute(destination);
    }

    public void update(Destination destination) {
        new UpdateAsyncTask(mAppDao).execute(destination);
    }

    private static class InsertAsyncTask extends DbAsyncTask<Destination> {

        InsertAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mAsyncAppDao.insertDestination(destinations[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends DbAsyncTask<Destination> {

        UpdateAsyncTask(AppDao appDao) {
            super(appDao);
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mAsyncAppDao.updateDestination(destinations[0]);
            return null;
        }
    }
}
