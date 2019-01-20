package com.rjdeleon.tourista.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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
