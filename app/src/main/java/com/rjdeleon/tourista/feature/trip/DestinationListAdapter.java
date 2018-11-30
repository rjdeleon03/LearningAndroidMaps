package com.rjdeleon.tourista.feature.trip;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;

import java.util.List;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestinationViewHolder> {

    private List<Destination> mDestinations;
    private LayoutInflater mInflater;

    public DestinationListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public DestinationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.item_destination, viewGroup, false);
        return new DestinationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationViewHolder destinationViewHolder, int i) {
        if (mDestinations == null) return;

        Destination destination = mDestinations.get(i);
        destinationViewHolder.title.setText(destination.getPlace());
    }

    @Override
    public int getItemCount() {
        return mDestinations == null ? 0 : mDestinations.size();
    }

    public void setDestinations(List<Destination> destinations) {
        mDestinations = destinations;
        notifyDataSetChanged();
    }

    class DestinationViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        private DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.destinationTitle);
        }
    }
}
