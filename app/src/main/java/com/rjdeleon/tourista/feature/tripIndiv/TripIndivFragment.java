package com.rjdeleon.tourista.feature.tripIndiv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.core.base.BaseFragment;
import com.rjdeleon.tourista.databinding.FragmentTripIndivBinding;
import com.rjdeleon.tourista.feature.tripDialog.TripDialogFragment;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripIndivFragment extends BaseFragment {

    private TripIndivViewModel mViewModel;
    private TripIndivDestinationsAdapter mAdapter;

    private long mId = 0;

    public TripIndivFragment() {
        // Required empty public constructor
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
            mViewModel.getDestinations().observe(this, destinations -> mAdapter.setDestinations(destinations));
            mViewModel.getTrip().observe(this, trip -> getActionBar().setTitle(trip.getName()));

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
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        getActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_trip_indiv, menu);

        menu.findItem(R.id.action_edit_trip_name).setOnMenuItemClickListener(item -> {

            TripDialogFragment tdf = TripDialogFragment.newInstance(mId);
            assert getFragmentManager() != null;
            tdf.show(getFragmentManager(), TripDialogFragment.TAG);
            return true;
        });
    }

    @OnClick(R.id.addDestinationButton)
    void onAddDestinationClick(View view) {

        TripIndivFragmentDirections.ActionTripIndivFragmentToDestinationFragment action =
                TripIndivFragmentDirections.actionTripIndivFragmentToDestinationFragment();
        action.setTripId(mId);
        Navigation.findNavController(view).navigate(action);
    }
}
