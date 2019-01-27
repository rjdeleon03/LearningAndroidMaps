package com.rjdeleon.tourista.feature.tripDialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.core.base.BaseDialogFragment;
import com.rjdeleon.tourista.databinding.DialogTripBinding;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

public class TripDialogFragment extends BaseDialogFragment {

    // region Static methods and properties

    public static final String TAG = "TRIP_DIALOG";
    private static final String TAG_TRIP_ID = "TRIP_ID";

    public static TripDialogFragment newInstance() {
        return new TripDialogFragment();
    }

    public static TripDialogFragment newInstance(long id) {

        TripDialogFragment fragment = new TripDialogFragment();
        Bundle args = new Bundle();
        args.putLong(TAG_TRIP_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    // endregion

    private TripDialogViewModel mViewModel;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_new_trip_title)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    mViewModel.save();
                    dismiss();
                })
                .setNegativeButton("Cancel", (dialogInterface, i) -> dismiss());

        // Get the trip ID
        long tripId = 0;
        Bundle args = getArguments();
        if (args != null && getActivity() != null) {
            tripId = args.getLong(TAG_TRIP_ID);
            b.setTitle(R.string.dialog_edit_trip_title);
        }

        // Set the viewModel
        TripDialogViewModelFactory factory =
                new TripDialogViewModelFactory(Objects.requireNonNull(getActivity()).getApplication(), tripId);
        mViewModel = ViewModelProviders.of(this, factory).get(TripDialogViewModel.class);

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        DialogTripBinding binding = DialogTripBinding.inflate(inflater);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        b.setView(binding.getRoot());
        return b.create();
    }
}
