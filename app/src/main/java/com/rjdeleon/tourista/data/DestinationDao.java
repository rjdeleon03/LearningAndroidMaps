package com.rjdeleon.tourista.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

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
