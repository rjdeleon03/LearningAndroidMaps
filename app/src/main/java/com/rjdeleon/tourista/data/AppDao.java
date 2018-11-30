package com.rjdeleon.tourista.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AppDao {

    @Insert
    void insertTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Query("DELETE FROM trip_table WHERE id = :tripId")
    void deleteTrip(long tripId);

    @Query("SELECT * FROM trip_table")
    LiveData<List<Trip>> getAllTrips();

    @Query("SELECT * FROM trip_table WHERE id = :tripId")
    LiveData<Trip> getTrip(long tripId);

    @Insert
    void insertDestination(Destination destination);

    @Update
    void updateDestination(Destination destination);

    @Query("DELETE FROM dest_table WHERE id = :destId")
    void deleteDestination(long destId);

    @Query("SELECT * FROM dest_table WHERE tripId = :tripId")
    LiveData<List<Destination>> getDestinationsPerTrip(long tripId);

    @Query("SELECT * FROM dest_table WHERE id = :destId")
    LiveData<Destination> getDestination(long destId);
}
