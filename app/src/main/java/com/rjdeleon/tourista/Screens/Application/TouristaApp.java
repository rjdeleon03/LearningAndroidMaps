package com.rjdeleon.tourista.Screens.Application;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.annotation.UiThread;

import com.rjdeleon.tourista.Constants;
import com.rjdeleon.tourista.Data.DaoAccess;
import com.rjdeleon.tourista.Data.DestinationDatabase;
import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

public class TouristaApp extends Application {

    private CompositionRoot _compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        _compositionRoot = _compositionRoot;
    }

    public CompositionRoot getCompositionRoot() {
        return _compositionRoot;
    }
}
