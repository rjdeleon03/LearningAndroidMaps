package com.rjdeleon.tourista.feature.destination;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.databinding.FragmentDestinationBinding;

import org.joda.time.DateTime;

import java.util.Date;
import java.util.Objects;

import androidx.navigation.Navigation;

public class DestinationFragment extends Fragment {

    private DestinationViewModel mViewModel;
    private TextView mDestinationDateText;
    private TextView mDestinationTimeText;
    private FloatingActionButton mSaveButton;

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
        setupDateTextView(view);
        setupTimeTextView(view);
        setupSaveButton(view);
        return view;
    }

    private void setupSaveButton(View view) {
        mSaveButton = view.findViewById(R.id.saveDestinationButton);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.save();
                Navigation.findNavController(v).navigateUp();
            }
        });
    }

    private void setupDateTextView(View view) {
        final DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

            }
        };

        mDestinationDateText = view.findViewById(R.id.destinationDateText);
        mDestinationDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getDate();

                DatePickerDialog d =
                        new DatePickerDialog(Objects.requireNonNull(getContext()), listener,
                                dt.getYear(), dt.getMonthOfYear() - 1, dt.getDayOfMonth());
                d.show();
            }
        });
    }

    private void setupTimeTextView(View view) {
        final TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int h, int m) {

            }
        };

        mDestinationTimeText = view.findViewById(R.id.destinationTimeText);
        mDestinationTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DateTime dt = Objects.requireNonNull(mViewModel.getDestination().getValue()).getDate();
                TimePickerDialog d =
                        new TimePickerDialog(Objects.requireNonNull(getContext()), listener,
                                dt.getHourOfDay(), dt.getMinuteOfHour(), true);
                d.show();
            }
        });
    }
}
