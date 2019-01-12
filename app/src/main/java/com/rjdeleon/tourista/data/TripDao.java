package com.rjdeleon.tourista.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Query("SELECT * from trips")
    LiveData<List<Trip>> getAll();

    @Query("SELECT * from trips WHERE id = :id")
    LiveData<Trip> findById(long id);

    @Insert
    void insert(Trip trip);

    @Update
    void update(Trip trip);

    @Delete
    void delete(Trip trip);
}
