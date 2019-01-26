package com.rjdeleon.tourista.data;

import com.rjdeleon.tourista.BR;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
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
    private String placeId;
    private String name;
    private double lat;
    private double lng;
    private long eventId;

    private String address;
    private String notes;
    private long tripId;

    @TypeConverters({DateTimeConverters.class})
    private DateTime startTime;
    @TypeConverters({DateTimeConverters.class})
    private DateTime endTime;
    private boolean isAllDay;
    private String timeZone;

    public Destination(long id, String name, String placeId,
                       double lat, double lng, long eventId, long tripId,
                       String address, String notes, DateTime startTime, DateTime endTime,
                       boolean isAllDay, String timeZone) {
        this.id = id;
        this.placeId = placeId;
        this.name = name;
        this.lat = lat;
        this.lng = lng;
        this.eventId = eventId;
        this.tripId = tripId;
        this.address = address;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAllDay = isAllDay;
        this.timeZone = timeZone;
    }

    @Ignore
    public Destination(String name, String placeId,
                       double lat, double lng, long eventId, long tripId,
                       String address, String notes, DateTime startTime, DateTime endTime,
                       boolean isAllDay) {
        this.name = name;
        this.placeId = placeId;
        this.lat = lat;
        this.lng = lng;
        this.eventId = eventId;
        this.tripId = tripId;
        this.address = address;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAllDay = isAllDay;
    }

    @Ignore
    public Destination(long tripId) {
        this.tripId = tripId;
        this.placeId = "";
        this.name = "";
        this.address = "";
        this.notes = "";
        this.startTime = DateTime.now()
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
        this.endTime = DateTime.now()
                .withSecondOfMinute(0)
                .withMillisOfSecond(0);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
        notifyPropertyChanged(BR.lat);
    }

    @Bindable
    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
        notifyPropertyChanged(BR.lng);
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
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
    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
        notifyPropertyChanged(BR.startTime);

        /* On start time set, end time must be 10 minutes later */
        if (Minutes.minutesBetween(startTime, endTime).getMinutes() < 10) {
            setEndTime(this.startTime.plusMinutes(10));
        }
    }

    @Bindable
    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
        notifyPropertyChanged(BR.endTime);

        /* On end time set, start time must be 10 minutes earlier */
        if (Minutes.minutesBetween(endTime, startTime).getMinutes() < 10) {
            setStartTime(this.endTime.minusMinutes(10));
        }
    }

    public boolean isAllDay() {
        return isAllDay;
    }

    public void setAllDay(boolean allDay) {
        isAllDay = allDay;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public void setPlaceDetails(String placeId, String name,
                                String address, double lat, double lng) {
        this.placeId = placeId;
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
        notifyChange();
    }
}
