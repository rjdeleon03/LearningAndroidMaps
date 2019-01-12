package com.rjdeleon.tourista.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "destinations",
    indices = { @Index("tripId") },
    foreignKeys = { @ForeignKey(entity = Trip.class,
        parentColumns = "id",
        childColumns = "tripId",
        onDelete = CASCADE)})
public class Destination {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private String address;
    private String notes;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private long tripId;

    public Destination(long id, String name, String address, String notes,
                       int year, int month, int day, int hour, int minute, long tripId) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.notes = notes;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.tripId = tripId;
    }

    @Ignore
    public Destination(String name, String address, String notes,
                       int year, int month, int day, int hour, int minute, long tripId) {
        this.name = name;
        this.address = address;
        this.notes = notes;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.tripId = tripId;
    }

    @Ignore
    public Destination() {}

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }
}
