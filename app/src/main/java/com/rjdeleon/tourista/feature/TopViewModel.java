package com.rjdeleon.tourista.feature;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TopViewModel extends AndroidViewModel {

    private Application mApplication;
    private TopRepository mRepository;
    private LiveData<Trip> mCachedTrip;
    private LiveData<List<Destination>> mCachedDestinations;

    private TopViewModel(@NonNull Application application, String id) {
        super(application);
        mApplication = application;
    }

    public TopViewModel(@NonNull Application application) {
        this(application, null);
    }

    private void initialize(String id) {
        mRepository = new TopRepository(mApplication, id);
        mCachedTrip = mRepository.getCachedTrip();
        mCachedDestinations = mRepository.getCachedDestinations();
    }

    public void initialize() {
        initialize(null);
    }

    public void cleanUp() {
        mRepository = null;
        mCachedTrip = null;
        mCachedDestinations = null;
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
