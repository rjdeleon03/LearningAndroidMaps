package com.rjdeleon.tourista.feature.triplist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TripListViewModel extends AndroidViewModel {

    private TripListRepository mRepository;
    private LiveData<List<Trip>> mTrips;

    public TripListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TripListRepository(application);
        mTrips = mRepository.getTrips();
    }

    public LiveData<List<Trip>> getTrips() {
        return mTrips;
    }

    public void insert(Trip trip) {
        mRepository.insert(trip);
    }

    public void delete(String id) {
        mRepository.delete(id);
    }
}
