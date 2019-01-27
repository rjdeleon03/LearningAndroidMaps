package com.rjdeleon.tourista.feature.timeZonePicker;

import android.app.Application;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.rjdeleon.tourista.data.CustomTimeZone;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class TimeZonePickerViewModel extends AndroidViewModel {

    private final MediatorLiveData<List<CustomTimeZone>> mTimeZones = new MediatorLiveData<>();
    private final MutableLiveData<String> mFilter = new MutableLiveData<>();
    private final TimeZonePickerRepository mRepository;

    public TimeZonePickerViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TimeZonePickerRepository(application);

        LiveData<List<CustomTimeZone>> timeZoneList = Transformations.switchMap(mFilter,
                filter -> {
                    if (filter.isEmpty()) return mRepository.getTimeZones();
                    return mRepository.getTimeZonesByFilter(filter);
                });
        mTimeZones.addSource(timeZoneList, mTimeZones::setValue);
        mFilter.setValue("");
    }

    LiveData<List<CustomTimeZone>> getTimeZones() {
        return mTimeZones;
    }

    void setFilter(String filter) {
        mFilter.setValue(filter);
    }
}
