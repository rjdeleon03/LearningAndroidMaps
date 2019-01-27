package com.rjdeleon.tourista.feature.timeZonePicker;


import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.databinding.FragmentTimeZonePickerBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimeZonePickerFragment extends Fragment {

    private TimeZonePickerViewModel mViewModel;
    private TimeZonePickerAdapter mAdapter;

    @BindView(R.id.timeZoneSearchView)
    SearchView timeZoneSearchView;

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
        ButterKnife.bind(this, view);
        mViewModel = ViewModelProviders.of(this).get(TimeZonePickerViewModel.class);

        mAdapter = new TimeZonePickerAdapter(getContext());
        mViewModel.getTimeZones().observe(this, timeZones -> mAdapter.setTimeZones(timeZones));

        binding.timeZoneRecyclerView.setAdapter(mAdapter);
        binding.setLifecycleOwner(this);

        timeZoneSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mViewModel.setFilter(newText);
                return true;
            }
        });

        return view;
    }

}
