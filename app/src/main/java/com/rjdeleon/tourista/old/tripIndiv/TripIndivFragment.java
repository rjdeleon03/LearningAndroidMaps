package com.rjdeleon.tourista.old.tripIndiv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.core.base.BaseFragment;
import com.rjdeleon.tourista.databinding.FragmentTripIndivBinding;
import com.rjdeleon.tourista.old.tripDialog.TripDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                 mViewModel.deleteDestination(viewHolder.getAdapterPosition());
            }
        }).attachToRecyclerView(binding.destinationsRecyclerView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (mViewModel != null)
            mViewModel.getTrip().observe(this, trip -> getActionBar().setTitle(trip.getName()));
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_trip_indiv, menu);

        menu.findItem(R.id.action_edit_trip_name).setOnMenuItemClickListener(item -> {

            if (mDialogFragment != null) return true;

            TripDialogFragment tdf = TripDialogFragment.newInstance(mId);
            tdf.setDismissListener(() -> mDialogFragment = null);
            mDialogFragment = tdf;

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