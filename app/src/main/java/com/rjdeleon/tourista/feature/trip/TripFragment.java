package com.rjdeleon.tourista.feature.trip;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.feature.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripFragment extends BaseFragment {

    private TripViewModel mTripViewModel;
    private DestinationListAdapter mAdapter;

    private FloatingActionButton addDestButton;
    private RecyclerView recyclerView;

    public TripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new DestinationListAdapter(getContext());
        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        mTripViewModel.getTrip().observe(this, new Observer<Trip>() {
            @Override
            public void onChanged(@Nullable Trip trip) {
                if (trip == null) return;
                mAdapter.setDestinations(trip.getDestinations());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip, container, false);

        addDestButton = view.findViewById(R.id.addDestButton);
        addDestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navController != null) {
                    navController.navigate(R.id.action_tripFragment_to_destinationFragment);
                }
            }
        });

        mTripViewModel = ViewModelProviders.of(this).get(TripViewModel.class);
        mTripViewModel.getTrip().observe(this, new Observer<Trip>() {
            @Override
            public void onChanged(@Nullable Trip trip) {

            }
        });

        return view;
    }
}
