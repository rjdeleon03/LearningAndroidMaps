package com.rjdeleon.tourista.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "trips")
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    public Trip(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Trip(String name) {
        this.name = name;
    }

    @Ignore
    public Trip() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
