package com.rjdeleon.tourista.feature.triplist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.feature.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TripListFragment extends BaseFragment {

    private FloatingActionButton addTripButton;

    public TripListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_trip_list, container, false);

        addTripButton = view.findViewById(R.id.addTripButton);
        addTripButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(navController != null)
                    navController.navigate(R.id.action_tripListFragment_to_tripFragment);
            }
        });

        return view;
    }
}
