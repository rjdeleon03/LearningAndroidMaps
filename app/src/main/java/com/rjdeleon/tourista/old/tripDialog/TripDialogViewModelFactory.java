package com.rjdeleon.tourista.old.tripDialog;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class TripDialogViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final long mId;

    TripDialogViewModelFactory(Application application, long id) {
        this.mApplication = application;
        this.mId = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new TripDialogViewModel(mApplication, mId);
    }
}
