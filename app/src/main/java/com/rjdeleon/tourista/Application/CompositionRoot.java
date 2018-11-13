package com.rjdeleon.tourista.Application;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.UiThread;

import com.rjdeleon.tourista.Constants;
import com.rjdeleon.tourista.Data.DaoAccess;
import com.rjdeleon.tourista.Data.DestinationDatabase;
import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

@UiThread
public class CompositionRoot {

    private Context _appContext;
    private DestinationDatabase _destinationDatabase;
    private DaoAccess _daoAccess;

    public CompositionRoot(Context appContext) {
        _appContext = appContext;
    }

    @UiThread
    // TODO: Set to private
    public DestinationDatabase getDatabase() {
        if (_destinationDatabase == null) {
            return _destinationDatabase = Room.databaseBuilder(_appContext,
                    DestinationDatabase.class,
                    Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return _destinationDatabase;
    }

    @UiThread
    private DaoAccess getDaoAccess() {
        if (_daoAccess == null) {
            _daoAccess = getDatabase().daoAccess();
        }
        return _daoAccess;
    }

    @UiThread
    public FetchDestinationsListUseCase getFetchDestinationsListUseCase() {
        return new FetchDestinationsListUseCase(getDaoAccess());
    }

}
