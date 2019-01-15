package com.rjdeleon.tourista.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.rjdeleon.tourista.BR;

import org.joda.time.DateTime;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "destinations",
    indices = { @Index("tripId") },
    foreignKeys = { @ForeignKey(entity = Trip.class,
        parentColumns = "id",
        childColumns = "tripId",
        onDelete = CASCADE)})
public class Destination extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String address;
    private String notes;

    @TypeConverters({Converters.class})
    private DateTime date;
    private long tripId;

    public Destination(long id, String name, String address, String notes,
                       DateTime date, long tripId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.notes = notes;
        this.date = date;
        this.tripId = tripId;
    }

    @Ignore
    public Destination(String name, String address, String notes,
                       DateTime date, long tripId) {
        this.name = name;
        this.address = address;
        this.notes = notes;
        this.date = date;
        this.tripId = tripId;
    }

    @Ignore
    public Destination(long tripId) {
        this.tripId = tripId;
        this.name = "";
        this.address = "";
        this.notes = "";
        this.date = DateTime.now()
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Bindable
    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
        notifyPropertyChanged(BR.date);
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }
}
