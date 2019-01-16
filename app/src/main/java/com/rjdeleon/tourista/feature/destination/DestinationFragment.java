package com.rjdeleon.tourista.feature.destination;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.databinding.FragmentDestinationBinding;

import org.joda.time.DateTime;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DestinationFragment extends Fragment {

    private DestinationViewModel mViewModel;

    public DestinationFragment() {
        // Required empty public constructor
    }

    public static DestinationFragment newInstance() {
        return new DestinationFragment();
    }

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentDestinationBinding binding = FragmentDestinationBinding.inflate(inflater, container, false);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.saveDestinationButton)
    public void onSaveButtonClick(View view) {
        mViewModel.save();
        Navigation.findNavController(view).navigateUp();
    }

    @OnClick(R.id.destinationDateText)
    public void onDateTextClick() {
        DatePickerDialog.OnDateSetListener listener = (datePicker, y, m, d) -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setDate(dest.getDate().withDate(y, m+1, d));
        };

        DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getDate();

        DatePickerDialog d =
                new DatePickerDialog(Objects.requireNonNull(getContext()), listener,
                        dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
        d.show();
    }

    @OnClick(R.id.destinationTimeText)
    public void onTimeTextClick() {
        TimePickerDialog.OnTimeSetListener listener = (timePicker, h, m) -> {
            Destination dest = Objects.requireNonNull(mViewModel.getDestination().getValue());
            dest.setDate(dest.getDate().withHourOfDay(h).withMinuteOfHour(m));
        };

        DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getDate();
        TimePickerDialog d =
                new TimePickerDialog(Objects.requireNonNull(getContext()), listener,
                        dt.getHourOfDay(), dt.getMinuteOfHour(), true);
        d.show();
    }
}
