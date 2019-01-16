package com.rjdeleon.tourista.feature.tripIndiv;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TripIndivViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final long mId;

    TripIndivViewModelFactory(Application application, long id) {
        this.mApplication = application;
        this.mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TripIndivViewModel(mApplication, mId);
    }
}
