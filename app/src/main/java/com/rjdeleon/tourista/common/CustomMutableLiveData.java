package com.rjdeleon.tourista.common;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.databinding.Observable;

public class CustomMutableLiveData<T extends BaseObservable>
        extends MutableLiveData<T> {

    @Override
    public void setValue(T value) {
        super.setValue(value);
        value.addOnPropertyChangedCallback(callback);
    }

    private Observable.OnPropertyChangedCallback callback =
            new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            setValue(getValue());
        }
    };
}
