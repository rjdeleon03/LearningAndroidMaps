package com.rjdeleon.tourista;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.rjdeleon.tourista.Data.DestinationDatabase;

public class TouristaApp extends Application {

    private DestinationDatabase _destinationDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        getDatabase();
    }

    public DestinationDatabase getDatabase() {
        if (_destinationDatabase == null) {
            return _destinationDatabase = Room.databaseBuilder(getApplicationContext(),
                    DestinationDatabase.class,
                    Constants.DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return _destinationDatabase;
    }
}
