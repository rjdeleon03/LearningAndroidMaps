package com.rjdeleon.tourista.feature.destination;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Destination;

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
