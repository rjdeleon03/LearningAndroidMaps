package com.rjdeleon.tourista.feature.destination;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.data.Destination;

public class DestinationViewModel extends AndroidViewModel {

    private DestinationRepository mRepository;
    private LiveData<Destination> mDestination;

    public DestinationViewModel(@NonNull Application application, long id) {
        super(application);
        mRepository = new DestinationRepository(application, id);
        mDestination = mRepository.getDestination();
    }

    public LiveData<Destination> getDestination() {
        return mDestination;
    }

    public void insert(Destination destination) {
        mRepository.insert(destination);
    }

    public void update(Destination destination) {
        mRepository.update(destination);
    }
}
