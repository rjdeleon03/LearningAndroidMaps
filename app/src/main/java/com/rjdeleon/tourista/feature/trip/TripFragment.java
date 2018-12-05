package com.rjdeleon.tourista.feature.trip;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @BindView(R.id.tripNameField) TextView tripNameField;
    @BindView(R.id.destinationList) RecyclerView recyclerView;

    public TripFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mAdapter = new DestinationListAdapter(context);

        FragmentActivity af = getActivity();
        assert af != null;
        mTopViewModel = ViewModelProviders.of(af).get(TopViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip, container, false);
        ButterKnife.bind(this, view);

        if (getArguments() != null) {
            mTopViewModel.initialize(TripFragmentArgs.fromBundle(getArguments()).getTripId());
        } else {
            mTopViewModel.initialize();
        }

        FragmentActivity af = getActivity();
        assert af != null;
        mTopViewModel.getCachedTrip().observe(af, trip -> {
            if (trip == null) return;
            tripNameField.setText(trip.getName());
        });

        mTopViewModel.getCachedDestinations().observe(af, destinations -> {
            if (destinations == null) return;
            mAdapter.setDestinations(destinations);
        });

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mTopViewModel.deleteDestination(
                        mAdapter.getDestinationAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onDetach() {
        if (isRemoving())
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
        String tripName = tripNameField.getText().toString().trim();
        List<Destination> destinations = mTopViewModel.getCachedDestinations().getValue();
        if (tripName.isEmpty() || destinations == null || destinations.isEmpty()) return;

        Trip trip = mTopViewModel.getCachedTrip().getValue();
        assert trip != null;
        trip.setName(tripName);

        mTopViewModel.insertOrUpdateTrip(trip);

        /* Navigate back */
        navController.navigateUp();
    }
}
