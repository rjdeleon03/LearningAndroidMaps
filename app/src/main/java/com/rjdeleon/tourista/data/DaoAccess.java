package com.rjdeleon.tourista.data;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

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
