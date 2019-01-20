package com.rjdeleon.tourista.feature.tripDialog;

import android.app.Application;

import com.rjdeleon.tourista.data.Trip;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

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

    void save() {
        mRepository.save();
    }
}
