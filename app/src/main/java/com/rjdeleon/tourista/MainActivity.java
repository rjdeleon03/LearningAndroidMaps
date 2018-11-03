package com.rjdeleon.tourista;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private GoogleMap _googleMap;
    private Marker _currMarker;

    private TextView _dateTextView;
    private TextView _timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _dateTextView = findViewById(R.id.dateField);
        _timeTextView = findViewById(R.id.timeField);

        SupportPlaceAutocompleteFragment placeAutocompleteFragment =
                (SupportPlaceAutocompleteFragment) getSupportFragmentManager().findFragmentById(R.id.placesFragment);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapsFragment);
        mapFragment.getMapAsync(this);

        assert placeAutocompleteFragment != null;
        placeAutocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                System.out.println("Place: " + place.getName());

                LatLng placeCoords = place.getLatLng();

                if (_currMarker != null) _currMarker.remove();

                _currMarker = _googleMap.addMarker(new MarkerOptions().position(placeCoords).title(place.getName().toString()));
                _googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(placeCoords, 12.0f));

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                System.out.println("An error occurred: " + status);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _googleMap = googleMap;
    }

    public void onSetDate(View view) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
        _dateTextView.setText(getString(R.string.date_field_template, y, m+1, d));
    }

    public void onSetTime(View view) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        TimePickerDialog dialog = new TimePickerDialog(this, this,
                calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),  false);
        dialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hr, int min) {
        String amOrPm = hr+1 > 12 ? "PM" : "AM";
        int actualHr = hr%12 == 0 ? 12 : hr%12;
        _timeTextView.setText(getString(R.string.time_field_template, actualHr, min, amOrPm));
    }
}
