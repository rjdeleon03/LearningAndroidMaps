package com.rjdeleon.tourista.Common.DependencyInjection;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.UiThread;

import com.rjdeleon.tourista.Constants;
import com.rjdeleon.tourista.Data.DaoAccess;
import com.rjdeleon.tourista.Data.DestinationDatabase;
import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Context _appContext;

    public ApplicationModule(Context appContext) {
        _appContext = appContext;
    }

    @Singleton
    @Provides
    DestinationDatabase getDatabase() {
        return Room.databaseBuilder(_appContext,
            DestinationDatabase.class,
            Constants.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build();
    }

    @Singleton
    @Provides
    DaoAccess getDaoAccess(DestinationDatabase database) {
        return database.daoAccess();
    }

    @Provides
    FetchDestinationsListUseCase getFetchDestinationsListUseCase(DaoAccess daoAccess) {
        return new FetchDestinationsListUseCase(daoAccess);
    }
}
