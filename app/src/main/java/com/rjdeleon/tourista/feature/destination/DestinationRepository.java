package com.rjdeleon.tourista.feature.destination;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.AppDatabase;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.DestinationDao;

public class DestinationRepository {

    private final DestinationDao mDao;
    private final LiveData<Destination> mDestination;

    DestinationRepository(@NonNull Application application, long id, long tripId) {
        mDao = AppDatabase.getInstance(application.getApplicationContext()).destinationDao();

        if (id == 0) {
            Destination d = new Destination();
            d.setTripId(tripId);
            MutableLiveData<Destination> md = new MutableLiveData<>();
            md.setValue(d);
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
            new InsertAsyncTask(mDao).doInBackground(destination);
        } else {
            new UpdateAsyncTask(mDao).doInBackground(destination);
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
