package com.rjdeleon.tourista.feature.triplist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.feature.base.BaseFragment;
import com.rjdeleon.tourista.feature.trip.TripFragmentDirections;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

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
    public void onAttach(Context context) {
        super.onAttach(context);

        mAdapter = new TripListAdapter(context);
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                mTripListViewModel.deleteTrip(
                        mAdapter.getTripAt(viewHolder.getAdapterPosition()));
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @OnClick(R.id.addTripButton)
    void addTrip() {
        if(navController == null) return;
        navController.navigate(R.id.action_tripListFragment_to_tripFragment);
    }
}
