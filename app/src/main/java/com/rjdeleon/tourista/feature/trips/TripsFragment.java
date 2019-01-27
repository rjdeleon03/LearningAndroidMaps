package com.rjdeleon.tourista.feature.trips;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.core.base.BaseDialogFragment;
import com.rjdeleon.tourista.core.base.BaseFragment;
import com.rjdeleon.tourista.databinding.FragmentTripsBinding;
import com.rjdeleon.tourista.feature.tripDialog.TripDialogFragment;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TripsFragment extends BaseFragment {

    private TripsAdapter mAdapter;
    private TripsViewModel mViewModel;

    public TripsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdapter = new TripsAdapter(getContext());
        mViewModel = ViewModelProviders.of(this).get(TripsViewModel.class);
        mViewModel.getTrips().observe(this, trips -> mAdapter.setTrips(trips));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTripsBinding binding = FragmentTripsBinding.inflate(inflater, container, false);
        binding.tripsRecyclerView.setAdapter(mAdapter);
        binding.setLifecycleOwner(this);

        View view = binding.getRoot();
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(
                (Objects.requireNonNull((AppCompatActivity) getActivity()))
                        .getSupportActionBar()).setTitle(R.string.app_name);
    }

    @OnClick(R.id.addTripButton)
    void onAddTripButtonClick() {
        if (mDialogFragment != null) return;

        mDialogFragment = TripDialogFragment.newInstance();
        mDialogFragment.setDismissListener(() -> mDialogFragment = null);

        assert getFragmentManager() != null;
        mDialogFragment.show(getFragmentManager(), TripDialogFragment.TAG);
    }

}
