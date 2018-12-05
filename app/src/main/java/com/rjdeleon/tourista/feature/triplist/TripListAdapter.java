package com.rjdeleon.tourista.feature.triplist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {

    private List<Trip> mTrips;
    private final LayoutInflater mInflater;

    TripListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_trip, viewGroup, false);
        return new TripViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder viewHolder, int i) {
        if (mTrips == null) return;

        Trip trip = mTrips.get(i);
        viewHolder.tripName.setText(trip.getName());
    }

    @Override
    public int getItemCount() {
        return mTrips == null ? 0 : mTrips.size();
    }

    void setTrips(List<Trip> trips) {
        mTrips = trips;
        notifyDataSetChanged();
    }

    Trip getTripAt(int index) {
        if (mTrips == null || mTrips.size() <= index) return null;

        return mTrips.get(index);
    }

    class TripViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tripName) TextView tripName;

        TripViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
