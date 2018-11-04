package com.rjdeleon.tourista.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Destination.class}, version = 1, exportSchema = false)
public abstract class DestinationDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
    
}
