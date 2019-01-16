package com.rjdeleon.tourista.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DestinationDao {

    @Query("SELECT * from destinations WHERE id = :id")
    LiveData<Destination> findById(long id);

    @Query("SELECT * from destinations WHERE tripId = :tripId")
    LiveData<List<Destination>> findByTripId(long tripId);

    @Insert
    void insert(Destination destination);

    @Update
    void update(Destination destination);

    @Delete
    void delete(Destination destination);
}
