package com.rjdeleon.tourista.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "trips")
public class Trip {

    @PrimaryKey(autoGenerate = true)
    private String id;

    private String name;

    public Trip(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Trip(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
