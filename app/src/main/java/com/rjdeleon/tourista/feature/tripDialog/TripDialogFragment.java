package com.rjdeleon.tourista.feature.tripDialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.rjdeleon.tourista.databinding.DialogTripBinding;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

public class TripDialogFragment extends DialogFragment {

    // region Static methods and properties

    public static final String TAG = "TRIP_DIALOG";

    public static TripDialogFragment newInstance() {
        return new TripDialogFragment();
    }

    // endregion

    private TripDialogViewModel mViewModel;

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder b = new AlertDialog.Builder(getActivity())
                .setTitle("Create Trip")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mViewModel.save();
                        dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dismiss();
                    }
                });

        // Set the viewModel
        mViewModel = ViewModelProviders.of(this).get(TripDialogViewModel.class);

        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        DialogTripBinding binding = DialogTripBinding.inflate(inflater);
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        b.setView(binding.getRoot());
        return b.create();
    }
}
