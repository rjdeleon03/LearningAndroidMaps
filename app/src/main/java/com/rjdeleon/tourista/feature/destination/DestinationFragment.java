package com.rjdeleon.tourista.feature.destination;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.codetroopers.betterpickers.OnDialogDismissListener;
import com.codetroopers.betterpickers.timezonepicker.TimeZonePickerDialogFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.tasks.Task;
import com.rjdeleon.tourista.Constants;
import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.core.base.BaseFragment;
import com.rjdeleon.tourista.core.calendar.CalendarUtils;
import com.rjdeleon.tourista.core.places.PlaceAutocompleteAdapter;
import com.rjdeleon.tourista.core.sharedprefs.SharedPrefsUtils;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.databinding.FragmentDestinationBinding;

import org.joda.time.DateTime;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.rjdeleon.tourista.Constants.MAPVIEW_BUNDLE_KEY;

public class DestinationFragment extends BaseFragment implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = "DestinationFragment";
    private DestinationViewModel mViewModel;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GeoDataClient mGoogleApiClient;
    private NavController mNavController;

    private static final LatLngBounds LAT_LNG_BOUNDS =
            new LatLngBounds(new LatLng(-40, -168), new LatLng(71, 136));

    @BindView(R.id.destinationParentLayout)
    ViewGroup destinationParentLayout;

    @BindView(R.id.destinationAutocompleteText)
    AutoCompleteTextView autoCompleteTextView;

    @BindView(R.id.destinationMap)
    MapView mapView;

    public DestinationFragment() {
        // Required empty public constructor
    }

    // region Life cycle methods

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && getActivity() != null) {
            long id = DestinationFragmentArgs.fromBundle(args).getId();
            long tripId = DestinationFragmentArgs.fromBundle(args).getTripId();

            DestinationViewModelFactory factory =
                    new DestinationViewModelFactory(getActivity().getApplication(), id, tripId);
            mViewModel = ViewModelProviders.of(this, factory).get(DestinationViewModel.class);
        }
        mGoogleApiClient = Places.getGeoDataClient(Objects.requireNonNull(getContext()));
        mPlaceAutocompleteAdapter =
                new PlaceAutocompleteAdapter(getContext(), mGoogleApiClient, LAT_LNG_BOUNDS, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDestinationBinding binding = FragmentDestinationBinding.inflate(inflater, container, false);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        setHasOptionsMenu(true);
        setupAutocompleteView();
        setupGooglePlacesAndMaps(savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mNavController = Navigation.findNavController(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_destination, menu);

        menu.findItem(R.id.action_delete_destination).setOnMenuItemClickListener(item -> {
            onDeleteButtonClick();
            return true;
        });

        menu.findItem(R.id.action_save_destination).setOnMenuItemClickListener(item -> {
            onSaveButtonClick();
            return true;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setTitle("");
        getActionBar().setElevation(0);
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getActionBar().setElevation(Constants.TOOLBAR_ELEVATION);
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    // endregion

    // region OnClick listeners


//    @OnClick(R.id.saveDestinationButton)
    void onSaveButtonClick() {

        Destination destination = mViewModel.getDestination().getValue();
        if (destination == null || getContext() == null) return;

        long calendarId = SharedPrefsUtils.getCalendarId(getContext());

        /* If ID is not 0, update; else, save */
        if (destination.getId() > 0) {
            CalendarUtils.updateEvent(getContext(), destination, calendarId);
        } else {
            long eventId = CalendarUtils.createEvent(getContext(), destination, calendarId);
            destination.setEventId(eventId);
        }
        mViewModel.save();
        mNavController.navigateUp();
    }

//    @OnClick(R.id.deleteDestinationButton)
    void onDeleteButtonClick() {

        Destination destination = mViewModel.getDestination().getValue();
        if (destination == null || getContext() == null) return;

        CalendarUtils.deleteEvent(getContext(), destination);
        mViewModel.delete();

        mNavController.navigateUp();
    }

    @OnClick(R.id.destinationStartDateText)
    void onStartDateTextClick() {
        DatePickerDialog.OnDateSetListener listener = (datePicker, y, m, d) -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setStartTime(dest.getStartTime().withDate(y, m+1, d));
        };

        DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getStartTime();

        DatePickerDialog d =
                new DatePickerDialog(Objects.requireNonNull(getContext()), listener,
                        dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
        d.show();
    }

    @OnClick(R.id.destinationStartTimeText)
    void onStartTimeTextClick() {
        TimePickerDialog.OnTimeSetListener listener = (timePicker, h, m) -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setStartTime(dest.getStartTime().withHourOfDay(h).withMinuteOfHour(m));
        };

        DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getStartTime();
        TimePickerDialog d =
                new TimePickerDialog(Objects.requireNonNull(getContext()), listener,
                        dt.getHourOfDay(), dt.getMinuteOfHour(), true);
        d.show();
    }

    @OnClick(R.id.destinationEndDateText)
    void onEndDateTextClick() {
        DatePickerDialog.OnDateSetListener listener = (datePicker, y, m, d) -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setEndTime(dest.getEndTime().withDate(y, m+1, d));
        };

        DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getEndTime();

        DatePickerDialog d =
                new DatePickerDialog(Objects.requireNonNull(getContext()), listener,
                        dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
        d.show();
    }

//    @OnClick(R.id.destinationEndTimeText)
    void onEndTimeTextClick() {
        TimePickerDialog.OnTimeSetListener listener = (timePicker, h, m) -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setEndTime(dest.getEndTime().withHourOfDay(h).withMinuteOfHour(m));
        };

        DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getEndTime();
        TimePickerDialog d =
                new TimePickerDialog(Objects.requireNonNull(getContext()), listener,
                        dt.getHourOfDay(), dt.getMinuteOfHour(), true);
        d.show();
    }

    @OnClick(R.id.destinationTimeZoneText)
    void onTimeZoneTextClick() {
        if (mDialogFragment != null) return;

        TimeZonePickerDialogFragment.OnTimeZoneSetListener listener = tzi -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setTimeZone(tzi.mTzId);
        };

        OnDialogDismissListener dismissListener = dialoginterface -> mDialogFragment = null;

        assert getFragmentManager() != null;
        Bundle bundle = new Bundle();
        bundle.putString(TimeZonePickerDialogFragment.BUNDLE_TIME_ZONE,
                Objects.requireNonNull(mViewModel.getDestination().getValue()).getTimeZone());

        TimeZonePickerDialogFragment tzpd = new TimeZonePickerDialogFragment();
        tzpd.setOnDismissListener(dismissListener);
        tzpd.setArguments(bundle);
        tzpd.setOnTimeZoneSetListener(listener);
        mDialogFragment = tzpd;
        tzpd.show(getFragmentManager(), Constants.TIMEZONE_PICKER_FRAGMENT_KEY);
    }

    // endregion

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void setupGooglePlacesAndMaps(Bundle savedInstanceState) {

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mapView.onCreate(mapViewBundle);
    }

    private void setupAutocompleteView() {
        autoCompleteTextView.setAdapter(mPlaceAutocompleteAdapter);
        autoCompleteTextView.setOnItemClickListener((parent, view1, position, id) -> {

            final AutocompletePrediction prediction = mPlaceAutocompleteAdapter.getItem(position);
            assert prediction != null;
            Task<PlaceBufferResponse> placeResult = mGoogleApiClient.getPlaceById(prediction.getPlaceId());
            placeResult.addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    PlaceBufferResponse places = task.getResult();
                    assert places != null;
                    mViewModel.setPlace(places.get(0));
                    places.release();
                } else {
                    Log.e(TAG, "Place was not found.");
                }
            });
        });
    }
}
