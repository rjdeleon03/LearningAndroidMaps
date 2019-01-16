package com.rjdeleon.tourista.feature.destination;

import android.app.Application;
import android.os.AsyncTask;

import com.rjdeleon.tourista.core.common.CustomMutableLiveData;
import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.DestinationDao;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

class DestinationRepository {

    private final DestinationDao mDao;
    private final LiveData<Destination> mDestination;

    DestinationRepository(@NonNull Application application, long id, long tripId) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).destinationDao();

        if (id == 0) {
            CustomMutableLiveData<Destination> md = new CustomMutableLiveData<>();
            md.setValue(new Destination(tripId));
            mDestination = md;
        } else {
            mDestination = mDao.findById(id);
        }
    }

    LiveData<Destination> getDestination() {
        return mDestination;
    }

    void save() {

        /* If ID is not 0, update; else, save */
        Destination destination = mDestination.getValue();

        assert destination != null;
        if (destination.getId() == 0) {
            new InsertAsyncTask(mDao).execute(destination);
        } else {
            new UpdateAsyncTask(mDao).execute(destination);
        }
    }

    private static class InsertAsyncTask extends AsyncTask<Destination, Void, Void> {

        private DestinationDao mDao;

        InsertAsyncTask(DestinationDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mDao.insert(destinations[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<Destination, Void, Void> {

        private DestinationDao mDao;

        UpdateAsyncTask(DestinationDao dao) {
            mDao = dao;
        }

        @Override
        protected Void doInBackground(Destination... destinations) {
            mDao.update(destinations[0]);
            return null;
        }
    }
}
