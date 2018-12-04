package com.rjdeleon.tourista.feature.destination;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.feature.TopViewModel;
import com.rjdeleon.tourista.feature.base.BaseFragment;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinationFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener, OnMapReadyCallback, PlaceSelectionListener {

    private static final String ARG_DESTINATION_ID = "DESTINATION ID";

    @BindView(R.id.timeField) TextView timeField;
    @BindView(R.id.dateField) TextView dateField;
    @BindView(R.id.notesField) EditText notesField;

    private GoogleMap mGoogleMap;
    private Marker mMarker;
    private Calendar mCalendar;

    private TopViewModel mTopViewModel;

    public DestinationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param destId Destination ID.
     * @return A new instance of fragment DestinationFragment.
     */
    public static DestinationFragment newInstance(String destId) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DESTINATION_ID, destId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Get arguments and store globally
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        FragmentActivity af = getActivity();
        assert af != null;
        mTopViewModel = ViewModelProviders.of(af).get(TopViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination, container, false);
        ButterKnife.bind(this, view);

        initializeMapsAndPlaces();
        initializeTimeDateFields();

        return view;
    }

    //region Maps and places

    private void initializeMapsAndPlaces() {
        FragmentManager fm = getChildFragmentManager();

        SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.mapsPlaceFragment);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);

        SupportPlaceAutocompleteFragment placeFragment = (SupportPlaceAutocompleteFragment) fm.findFragmentById(R.id.placeSearchFragment);
        assert placeFragment != null;
        placeFragment.setOnPlaceSelectedListener(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
    }

    @Override
    public void onPlaceSelected(Place place) {
        assert mGoogleMap != null;

        LatLng placeCoords = place.getLatLng();
        if (mMarker != null) mMarker.remove();
        mMarker = mGoogleMap.addMarker(new MarkerOptions()
                .title(place.getName().toString())
                .position(placeCoords));

        mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(placeCoords, 12.0f));
    }

    @Override
    public void onError(Status status) {
        // TODO: Handle place fragment error
    }

    //endregion

    //region Date and time input

    private void initializeTimeDateFields() {
        mCalendar = Calendar.getInstance(TimeZone.getDefault());
        setDateTextViewContent(mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH), mCalendar.get(Calendar.DAY_OF_MONTH));
        setTimeTextViewContent(mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE));
    }

    @OnClick(R.id.timeField)
    void showTimePicker() {
        if (getContext() == null) return;
        TimePickerDialog dialog = new TimePickerDialog(getContext(), this,
                mCalendar.get(Calendar.HOUR_OF_DAY), mCalendar.get(Calendar.MINUTE),  false);
        dialog.show();
    }

    @OnClick(R.id.dateField)
    void showDatePicker() {
        if (getContext() == null) return;
        DatePickerDialog dialog = new DatePickerDialog(getContext(), this,
                mCalendar.get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

    private void setDateTextViewContent(int y, int m, int d) {
        dateField.setText(getString(R.string.date_field_template, y, m+1, d));
    }

    private void setTimeTextViewContent(int hr, int min) {
        String amOrPm = hr+1 > 12 ? "PM" : "AM";
        int actualHr = hr%12 == 0 ? 12 : hr%12;
        timeField.setText(getString(R.string.time_field_template, actualHr, min, amOrPm));
    }

    @Override
    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
        setDateTextViewContent(y, m, d);
        mCalendar.set(y, m, d,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hr, int min) {
        setTimeTextViewContent(hr, min);
        mCalendar.set(mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH),
                hr, min);
    }

    //endregion

    @OnClick(R.id.saveDestButton)
    void saveDestination() {
        if (mMarker == null) return;

        Destination destination = new Destination();
        destination.setPlace(mMarker.getTitle());
        destination.setLat(mMarker.getPosition().latitude);
        destination.setLng(mMarker.getPosition().longitude);
        destination.setNotes(notesField.getText().toString());
        destination.setTimestamp(mCalendar.getTime());

        mTopViewModel.insertDestination(destination);
        navController.navigateUp();
    }
}
