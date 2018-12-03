package com.rjdeleon.tourista.feature.triplist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.feature.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripListFragment extends BaseFragment {

    private TripListViewModel mTripListViewModel;
    private TripListAdapter mAdapter;

    @BindView(R.id.tripList) RecyclerView recyclerView;

    public TripListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new TripListAdapter(getContext());
        mTripListViewModel = ViewModelProviders.of(this).get(TripListViewModel.class);
        mTripListViewModel.getTrips().observe(this, new Observer<List<Trip>>() {
            @Override
            public void onChanged(@Nullable List<Trip> trips) {
                mAdapter.setTrips(trips);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @OnClick(R.id.addTripButton)
    void addTrip() {
        if(navController != null)
            navController.navigate(R.id.action_tripListFragment_to_tripFragment);
    }
}
