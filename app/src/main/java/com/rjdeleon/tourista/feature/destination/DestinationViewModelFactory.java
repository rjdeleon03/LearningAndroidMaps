package com.rjdeleon.tourista.feature.destination;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class DestinationViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final long mId;
    private final long mTripId;

    DestinationViewModelFactory(Application application, long id, long tripId) {
        this.mApplication = application;
        this.mId = id;
        this.mTripId = tripId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new DestinationViewModel(mApplication, mId, mTripId);
    }
}
