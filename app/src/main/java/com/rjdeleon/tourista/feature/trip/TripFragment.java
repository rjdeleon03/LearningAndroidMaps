package com.rjdeleon.tourista.feature.trip;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripFragment extends BaseFragment {

    private TopViewModel mTopViewModel;
    private DestinationListAdapter mAdapter;

    @BindView(R.id.destinationList) RecyclerView recyclerView;

    public TripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new DestinationListAdapter(getContext());

        FragmentActivity af = getActivity();

        assert af != null;
        mTopViewModel = ViewModelProviders.of(af).get(TopViewModel.class);
        mTopViewModel.initialize();

        mTopViewModel.getCachedTrip().observe(af, new Observer<Trip>() {
            @Override
            public void onChanged(@Nullable Trip trip) {
                if (trip == null) return;
                mAdapter.setDestinations(trip.getDestinations());
            }
        });

        mTopViewModel.getCachedDestinations().observe(af, new Observer<List<Destination>>() {
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
        ButterKnife.bind(this, view);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onDetach() {
        mTopViewModel.cleanUp();
        super.onDetach();
    }

    @OnClick(R.id.addDestButton)
    void addDestination() {
        if (navController != null) {
            navController.navigate(R.id.action_tripFragment_to_destinationFragment);
        }
    }

    @OnClick(R.id.saveTripButton)
    void saveTrip() {
        Trip trip = new Trip();
        trip.setName("Some Trip");
        mTopViewModel.insertTrip(trip);
        navController.navigateUp();
    }
}
