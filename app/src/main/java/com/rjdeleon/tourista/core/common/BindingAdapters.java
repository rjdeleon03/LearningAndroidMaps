package com.rjdeleon.tourista.core.common;

import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rjdeleon.tourista.data.Destination;

import org.joda.time.DateTime;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("dateText")
    public static void setDateText(TextView view, DateTime dateTime) {
        view.setText(dateTime.toString("yyyy/MM/dd"));
    }

    @BindingAdapter("timeText")
    public static void setTimeText(TextView view, DateTime dateTime) {
        view.setText(dateTime.toString("hh:mm"));
    }

    @BindingAdapter("mapPlace")
    public static void setPlace(MapView view, Destination destination) {
        view.getMapAsync(googleMap -> {
            LatLng latLng = new LatLng(destination.getLat(), destination.getLng());
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(destination.getName()));
            googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        });
    }
}
