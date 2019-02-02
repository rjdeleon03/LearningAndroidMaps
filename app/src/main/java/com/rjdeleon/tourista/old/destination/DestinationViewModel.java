package com.rjdeleon.tourista.old.destination;

import android.app.Application;

import com.google.android.gms.location.places.Place;
import com.rjdeleon.tourista.data.Destination;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DestinationViewModel extends AndroidViewModel {

    private final LiveData<Destination> mDestination;
    private final DestinationRepository mRepository;

    DestinationViewModel(@NonNull Application application, long id, long tripId) {
        super(application);
        mRepository = new DestinationRepository(application, id, tripId);
        mDestination = mRepository.getDestination();
    }

    public LiveData<Destination> getDestination() {
        return mDestination;
    }

    void save() {
        mRepository.save();
    }

    void delete() {
        mRepository.delete();
    }

    void setPlace(Place place) {
        Destination destination = mDestination.getValue();
        assert destination != null;
        destination.setPlaceDetails(
                
                place.getId(),
                place.getName().toString(),
                place.getAddress() != null ? place.getAddress().toString() : "",
                place.getLatLng().latitude,
                place.getLatLng().longitude);
    }
}