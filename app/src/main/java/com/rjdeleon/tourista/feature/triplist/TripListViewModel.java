package com.rjdeleon.tourista.feature.triplist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TripListViewModel extends AndroidViewModel {

    @SuppressWarnings("FieldCanBeLocal")
    private TripListRepository mRepository;
    private LiveData<List<Trip>> mTrips;

    public TripListViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TripListRepository(application);
        mTrips = mRepository.getTrips();
    }

    LiveData<List<Trip>> getTrips() {
        return mTrips;
    }
}
