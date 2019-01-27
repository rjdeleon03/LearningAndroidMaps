package com.rjdeleon.tourista.data;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "timeZones")
public class CustomTimeZone {

    @PrimaryKey(autoGenerate =  true)
    private long id;
    private String timeZoneId;
    private String displayName;

    public CustomTimeZone(long id, String timeZoneId, String displayName) {
        this.id = id;
        this.timeZoneId = timeZoneId;
        this.displayName = displayName;
    }

    @Ignore
    public CustomTimeZone(String timeZoneId, String displayName) {
        this.timeZoneId = timeZoneId;
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
