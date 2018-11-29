package com.rjdeleon.tourista.feature.destination;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.rjdeleon.tourista.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DestinationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DestinationFragment extends Fragment {

    private static final String ARG_DESTINATION_ID = "DESTINATION ID";

    private TextView timeField;
    private TextView dateField;
    private EditText notesField;
    private FloatingActionButton saveDestButton;

    public DestinationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param destId Destination ID.
     * @return A new instance of fragment DestinationFragment.
     */
    public static DestinationFragment newInstance(String destId) {
        DestinationFragment fragment = new DestinationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DESTINATION_ID, destId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // TODO: Get arguments and store globally
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_destination, container, false);

        timeField = view.findViewById(R.id.timeField);
        dateField = view.findViewById(R.id.dateField);
        notesField = view.findViewById(R.id.notesField);
        saveDestButton = view.findViewById(R.id.saveDestButton);

        return view;
    }
}
