package com.rjdeleon.tourista.feature.trips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.TripListViewHolder> {

    private LayoutInflater mInflater;
    private List<Trip> mTrips;

    TripsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new TripListViewHolder(mInflater.inflate(R.layout.item_trip, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TripListViewHolder viewHolder, int i) {
        Trip trip = mTrips.get(i);
        viewHolder.tripNameText.setText(trip.getName());
        viewHolder.setItemClickListener(createItemClickListener(trip.getId()));
    }

    @Override
    public int getItemCount() {
        return mTrips == null ? 0 : mTrips.size();
    }

    public void setTrips(List<Trip> trips) {
        mTrips = trips;
        notifyDataSetChanged();
    }

    private View.OnClickListener createItemClickListener(final long tripId) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TripsFragmentDirections.ActionTripsFragmentToTripIndivFragment action =
                        TripsFragmentDirections.actionTripsFragmentToTripIndivFragment();
                action.setTripId(tripId);
                Navigation.findNavController(view).navigate(action);
            }
        };
    }

    class TripListViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        TextView tripNameText;

        TripListViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            tripNameText = mItemView.findViewById(R.id.tripNameText);
        }

        void setItemClickListener(View.OnClickListener listener) {
            mItemView.setOnClickListener(listener);
        }
    }
}
