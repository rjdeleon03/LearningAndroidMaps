package com.rjdeleon.tourista.feature;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TopViewModel extends AndroidViewModel {

    private TopRepository mRepository;
    private LiveData<Trip> mCachedTrip;
    private LiveData<List<Destination>> mCachedDestinations;

    public TopViewModel(@NonNull Application application, String id) {
        super(application);
        mRepository = new TopRepository(application, id);
        mCachedTrip = mRepository.getCachedTrip();
        mCachedDestinations = mRepository.getCachedDestinations();
    }

    public TopViewModel(@NonNull Application application) {
        this(application, null);
    }

    public LiveData<Trip> getCachedTrip() {
        return mCachedTrip;
    }

    public LiveData<List<Destination>> getCachedDestinations() {
        return mCachedDestinations;
    }

    public void insertTrip(Trip trip) {
        mRepository.insertTrip(trip);
    }

    public void insertDestination(Destination destination) {
        mRepository.insertDestination(destination);
    }
}
