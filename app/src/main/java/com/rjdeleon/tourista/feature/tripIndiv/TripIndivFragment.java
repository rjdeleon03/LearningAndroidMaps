package com.rjdeleon.tourista.feature.tripIndiv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;
import com.rjdeleon.tourista.databinding.FragmentTripIndivBinding;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripIndivFragment extends Fragment {

    private TripIndivViewModel mViewModel;
    private TripIndivDestinationsAdapter mAdapter;

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

            mId = TripIndivFragmentArgs.fromBundle(getArguments()).getTripId();
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

        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.addDestinationButton)
    public void onAddDestinationClick(View view) {

        TripIndivFragmentDirections.ActionTripIndivFragmentToDestinationFragment action =
                TripIndivFragmentDirections.actionTripIndivFragmentToDestinationFragment();
        action.setTripId(mId);
        Navigation.findNavController(view).navigate(action);
    }
}
