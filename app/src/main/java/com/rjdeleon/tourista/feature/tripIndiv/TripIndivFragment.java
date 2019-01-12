package com.rjdeleon.tourista.feature.tripIndiv;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;

public class TripIndivFragment extends Fragment {

    public TripIndivFragment() {
        // Required empty public constructor
    }

    public static TripIndivFragment newInstance() {
        return new TripIndivFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_trip_indiv, container, false);
    }
}
