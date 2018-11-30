package com.rjdeleon.tourista.feature.base;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.rjdeleon.tourista.feature.destination.DestinationViewModel;
import com.rjdeleon.tourista.feature.trip.TripViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Application mApplication;
    private Object[] mParams;

    public ViewModelFactory(Application application, Object... params) {
        mApplication = application;
        mParams = params;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass == DestinationViewModel.class) {
            return (T) new DestinationViewModel(mApplication, (Long) mParams[0]);
//        } else if (modelClass == TripViewModel.class) {
//            return (T) new TripViewModel(mApplication, (Long) mParams[0]);
        } else {
            return super.create(modelClass);
        }
    }
}
