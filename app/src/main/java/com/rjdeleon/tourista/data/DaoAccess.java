package com.rjdeleon.tourista.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DaoAccess {

    @Insert
    void insertTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Delete
    void deleteTrip(Trip trip);

    @Query("SELECT * FROM trip_table")
    List<Trip> getAllTrips();

    @Query("SELECT * FROM trip_table WHERE id = :tripId")
    Trip getTrip(String tripId);

    @Insert
    void insertDestination(Destination destination);

    @Update
    void updateDestination(Destination destination);

    @Delete
    void deleteDestination(Destination destination);

    @Query("SELECT * FROM dest_table WHERE tripId = :tripId")
    List<Destination> getDestinationsPerTrip(String tripId);

    @Query("SELECT * FROM dest_table WHERE id = :destId")
    Destination getDestination(String destId);
}
