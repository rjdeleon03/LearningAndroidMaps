package com.rjdeleon.tourista.Data;

import com.rjdeleon.tourista.data.Destination;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface DaoAccess {

    @Insert
    void insertDestination (Destination destination);

    @Query("SELECT * FROM Destination")
    List<Destination> getAllDestinations();

    @Query("SELECT * FROM Destination WHERE id = :id")
    Destination getDestinationWithId(String id);

    @Update
    void updateDestination (Destination destination);

    @Delete
    void deleteDestination (Destination destination);
}
