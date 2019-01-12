package com.rjdeleon.tourista.feature.tripIndiv;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Trip;

public class TripIndivViewModel extends AndroidViewModel {

    private final LiveData<Trip> mTrip;
    private final TripIndivRepository mRepository;

    public TripIndivViewModel(@NonNull Application application, long id) {
        super(application);
        mRepository = new TripIndivRepository(application, id);
        mTrip = mRepository.getTrip();
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    void save() {
        mRepository.save();
    }
}
