package com.rjdeleon.tourista.feature.tripIndiv;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;

public class TripIndivFragment extends Fragment {

    private TripIndivViewModel mViewModel;

    public TripIndivFragment() {
        // Required empty public constructor
    }

    public static TripIndivFragment newInstance() {
        return new TripIndivFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null && getActivity() != null) {
            long tripId = TripIndivFragmentArgs.fromBundle(getArguments()).getTripId();

            TripIndivViewModelFactory factory = new TripIndivViewModelFactory(
                    getActivity().getApplication(), tripId);
            mViewModel = ViewModelProviders.of(this, factory).get(TripIndivViewModel.class);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_indiv, container, false);
    }
}
