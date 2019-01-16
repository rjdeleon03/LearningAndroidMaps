package com.rjdeleon.tourista.data;

import com.rjdeleon.tourista.BR;

import org.joda.time.DateTime;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.adapters.Converters;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import static androidx.room.ForeignKey.CASCADE;

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

    @TypeConverters({DateTimeConverters.class})
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
