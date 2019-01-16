package com.rjdeleon.tourista.feature.trips;

import android.app.Application;

import com.rjdeleon.tourista.data.Trip;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TripsViewModel extends AndroidViewModel {

    private TripsRepository mRepository;
    private final LiveData<List<Trip>> mTrips;

    public TripsViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TripsRepository(application);
        mTrips = mRepository.getTrips();
    }

    public LiveData<List<Trip>> getTrips() {
        return mTrips;
    }
}
