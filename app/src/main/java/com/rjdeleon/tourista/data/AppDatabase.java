package com.rjdeleon.tourista.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Trip.class, Destination.class},
    version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DaoAccess daoAccess();
}
