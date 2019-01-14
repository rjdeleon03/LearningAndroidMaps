package com.rjdeleon.tourista.feature.tripIndiv;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.databinding.FragmentTripIndivBinding;

import java.util.List;
import java.util.Objects;

import androidx.navigation.Navigation;

public class TripIndivFragment extends Fragment {

    private TripIndivViewModel mViewModel;
    private TripIndivDestinationsAdapter mAdapter;
    private FloatingActionButton mAddDestButton;

    private long mId = 0;

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

            mId = tripId;
            mAdapter = new TripIndivDestinationsAdapter(getContext());
            TripIndivViewModelFactory factory = new TripIndivViewModelFactory(
                    getActivity().getApplication(), mId);
            mViewModel = ViewModelProviders.of(this, factory).get(TripIndivViewModel.class);
            mViewModel.getDestinations().observe(this, new Observer<List<Destination>>() {
                @Override
                public void onChanged(@Nullable List<Destination> destinations) {
                    mAdapter.setDestinations(destinations);
                }
            });

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTripIndivBinding binding = FragmentTripIndivBinding.inflate(inflater, container, false);
        binding.destinationsRecyclerView.setAdapter(mAdapter);
        binding.setLifecycleOwner(this);

        setupAddDestinationButton(binding.getRoot());

        return binding.getRoot();
    }

    private void setupAddDestinationButton(View view) {
        mAddDestButton = view.findViewById(R.id.addDestinationButton);
        mAddDestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TripIndivFragmentDirections.ActionTripIndivFragmentToDestinationFragment action =
                        TripIndivFragmentDirections.actionTripIndivFragmentToDestinationFragment();
                action.setTripId(mId);
                Navigation.findNavController(v).navigate(action);
            }
        });
    }
}
