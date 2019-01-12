package com.rjdeleon.tourista.feature.tripDialog;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Trip;

public class TripDialogViewModel extends AndroidViewModel {

    private final LiveData<Trip> mTrip;
    private final TripDialogRepository mRepository;

    public TripDialogViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TripDialogRepository(application);
        mTrip = mRepository.getTrip();
    }

    public LiveData<Trip> getTrip() {
        return mTrip;
    }

    void save()
    {
        mRepository.save();
    }
}
