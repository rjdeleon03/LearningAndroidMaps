package com.rjdeleon.tourista.feature.tripIndiv;

import android.app.Application;

import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TripIndivViewModel extends AndroidViewModel {

    private final LiveData<Trip> mTrip;
    private final LiveData<List<Destination>> mDestinations;
    private final TripIndivRepository mRepository;

    TripIndivViewModel(@NonNull Application application, long id) {
        super(application);
        mRepository = new TripIndivRepository(application, id);
        mTrip = mRepository.getTrip();
        mDestinations = mRepository.getDestinations();
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    LiveData<List<Destination>> getDestinations() {
        return mDestinations;
    }

    void save() {
        mRepository.save();
    }
}
