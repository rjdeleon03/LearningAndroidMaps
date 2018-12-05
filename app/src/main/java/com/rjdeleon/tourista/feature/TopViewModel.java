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

    public TopViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
        mRepository = new TopRepository(mApplication);
    }

    public void initialize(String id) {
        mRepository.initialize(id);
        mCachedTrip = mRepository.getCachedTrip();
        mCachedDestinations = mRepository.getCachedDestinations();
    }

    public void initialize() {
        initialize(null);
    }

    public void cleanUp() {
        mRepository.cleanUp();
        mCachedTrip = null;
        mCachedDestinations = null;
    }

    public LiveData<Trip> getCachedTrip() {
        return mCachedTrip;
    }

    public LiveData<List<Destination>> getCachedDestinations() {
        return mCachedDestinations;
    }

    public void insertOrUpdateTrip(Trip trip) {
        if (trip.getId().isEmpty()) {
            insertTrip(trip);
        } else {
            updateTrip(trip);
        }
    }

    private void insertTrip(Trip trip) {
        trip.setDestinations(mCachedDestinations.getValue());
        mRepository.insertTrip(trip);
    }

    private void updateTrip(Trip trip) {
        trip.setDestinations(mCachedDestinations.getValue());
        mRepository.updateTrip(trip);
    }

    public void insertDestination(Destination destination) {
        mRepository.insertDestination(destination);
    }

    public void deleteDestination(Destination destination) {
        mRepository.deleteDestination(destination);
    }
}
