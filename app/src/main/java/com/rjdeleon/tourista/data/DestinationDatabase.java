package com.rjdeleon.tourista.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Destination.class}, version = 1, exportSchema = false)
@TypeConverters({TimestampConverter.class})
public abstract class DestinationDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
    
}
