package com.rjdeleon.tourista.feature.destination;

import android.app.Application;

import com.rjdeleon.tourista.data.Destination;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DestinationViewModel extends AndroidViewModel {

    private final LiveData<Destination> mDestination;
    private final DestinationRepository mRepository;

    public DestinationViewModel(@NonNull Application application, long id, long tripId) {
        super(application);
        mRepository = new DestinationRepository(application, id, tripId);
        mDestination = mRepository.getDestination();
    }

    public LiveData<Destination> getDestination() {
        return mDestination;
    }

    public void save() {
        mRepository.save();
    }
}
