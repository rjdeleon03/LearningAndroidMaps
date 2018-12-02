package com.rjdeleon.tourista.feature.trip;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.feature.TopViewModel;
import com.rjdeleon.tourista.feature.base.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripFragment extends BaseFragment {

    private TopViewModel mTopViewModel;
    private DestinationListAdapter mAdapter;

    private FloatingActionButton addDestButton;
    private FloatingActionButton saveTripButton;

    private RecyclerView recyclerView;

    public TripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new DestinationListAdapter(getContext());

        mTopViewModel = ViewModelProviders.of(getActivity()).get(TopViewModel.class);
        mTopViewModel.getCachedTrip().observe(getActivity(), new Observer<Trip>() {
            @Override
            public void onChanged(@Nullable Trip trip) {
                if (trip == null) return;
                mAdapter.setDestinations(trip.getDestinations());
            }
        });

        mTopViewModel.getCachedDestinations().observe(getActivity(), new Observer<List<Destination>>() {
            @Override
            public void onChanged(@Nullable List<Destination> destinations) {
                if (destinations == null) return;
                mAdapter.setDestinations(destinations);
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
            public void onClick(View view) { if (navController != null) {
                    navController.navigate(R.id.action_tripFragment_to_destinationFragment);
                }
            }
        });

        saveTripButton = view.findViewById(R.id.saveTripButton);
        saveTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Trip trip = new Trip();
                trip.setName("Some Trip");
                mTopViewModel.insertTrip(trip);
                navController.navigateUp();
            }
        });

        recyclerView = view.findViewById(R.id.destinationList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
