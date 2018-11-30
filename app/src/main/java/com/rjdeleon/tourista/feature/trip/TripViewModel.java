package com.rjdeleon.tourista.feature.trip;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Trip;

public class TripViewModel extends AndroidViewModel {

    private TripRepository mRepository;
    private LiveData<Trip> mTrip;

    public TripViewModel(@NonNull Application application, long id) {
        super(application);
        mRepository = new TripRepository(application, id);
        mTrip = mRepository.getTrip();
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    public void delete() {
        mRepository.delete();
    }

    public void deleteDestination(long id) {
        mRepository.deleteDestination(id);
    }
}
