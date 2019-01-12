package com.rjdeleon.tourista.feature.trips;


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
import com.rjdeleon.tourista.data.Trip;
import com.rjdeleon.tourista.databinding.FragmentTripsBinding;
import com.rjdeleon.tourista.feature.tripDialog.TripDialogFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TripsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TripsFragment extends Fragment {

    private TripsAdapter mAdapter;
    private TripsViewModel mViewModel;

    private FloatingActionButton mAddTripButton;

    public TripsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment TripsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TripsFragment newInstance() {
        return new TripsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new TripsAdapter(getContext());
        mViewModel = ViewModelProviders.of(this).get(TripsViewModel.class);
        mViewModel.getTrips().observe(this, new Observer<List<Trip>>() {
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
        FragmentTripsBinding binding = FragmentTripsBinding.inflate(inflater, container, false);
        binding.tripsRecyclerView.setAdapter(mAdapter);
        binding.setLifecycleOwner(this);

        setupAddTripButton(binding.getRoot());
        return binding.getRoot();
    }

    private void setupAddTripButton(View view) {
        mAddTripButton = view.findViewById(R.id.addTripButton);
        mAddTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TripDialogFragment tdf = TripDialogFragment.newInstance();
                assert getFragmentManager() != null;
                tdf.show(getFragmentManager(), TripDialogFragment.TAG);
            }
        });
    }

}
