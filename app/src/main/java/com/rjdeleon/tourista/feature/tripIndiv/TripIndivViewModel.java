package com.rjdeleon.tourista.feature.tripIndiv;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TripIndivViewModel extends AndroidViewModel {

    private final LiveData<Trip> mTrip;
    private final LiveData<List<Destination>> mDestinations;
    private final TripIndivRepository mRepository;

    public TripIndivViewModel(@NonNull Application application, long id) {
        super(application);
        mRepository = new TripIndivRepository(application, id);
        mTrip = mRepository.getTrip();
        mDestinations = mRepository.getDestinations();
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    public LiveData<List<Destination>> getDestinations() {
        return mDestinations;
    }

    void save() {
        mRepository.save();
    }
}
