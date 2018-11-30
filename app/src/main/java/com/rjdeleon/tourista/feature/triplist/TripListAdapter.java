package com.rjdeleon.tourista.feature.triplist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Trip;

import java.util.List;

public class TripListAdapter extends RecyclerView.Adapter<TripListAdapter.TripViewHolder> {

    private List<Trip> mTrips;
    private final LayoutInflater mInflater;

    public TripListAdapter(Context context) {
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

    }

    @Override
    public int getItemCount() {
        return mTrips == null ? 0 : mTrips.size();
    }

    public void setTrips(List<Trip> trips) {
        mTrips = trips;
        notifyDataSetChanged();
    }

    class TripViewHolder extends RecyclerView.ViewHolder {

        TripViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
