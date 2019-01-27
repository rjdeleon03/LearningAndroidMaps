package com.rjdeleon.tourista.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface CustomTimeZoneDao {

    @Query("SELECT count(*) FROM timeZones")
    int getCount();

    @Query("SELECT * from timeZones")
    LiveData<List<CustomTimeZone>> getAll();

    @Query("SELECT * from timeZones " +
            "WHERE timeZoneId LIKE :filter OR displayName LIKE :filter")
    LiveData<List<CustomTimeZone>> getByFilter(String filter);

    @Insert
    void insert(CustomTimeZone customTimeZone);
}
