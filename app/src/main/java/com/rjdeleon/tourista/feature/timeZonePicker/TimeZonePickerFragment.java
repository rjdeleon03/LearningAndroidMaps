package com.rjdeleon.tourista.feature.timeZonePicker;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.databinding.FragmentTimeZonePickerBinding;

import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeZonePickerFragment extends Fragment {

    private TimeZonePickerViewModel mViewModel;
    private TimeZonePickerAdapter mAdapter;

    public TimeZonePickerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        FragmentTimeZonePickerBinding binding =
                FragmentTimeZonePickerBinding.inflate(inflater, container, false);

        View view = binding.getRoot();
        mViewModel = ViewModelProviders.of(this).get(TimeZonePickerViewModel.class);

        mAdapter = new TimeZonePickerAdapter(getContext());
        mAdapter.setTimeZones(TimeZone.getAvailableIDs());

        binding.timeZoneRecyclerView.setAdapter(mAdapter);
        binding.setLifecycleOwner(this);

        return view;
    }

}
