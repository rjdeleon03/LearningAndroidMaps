package com.rjdeleon.tourista.feature.trip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestinationViewHolder> {

    private List<Destination> mDestinations;
    private LayoutInflater mInflater;

    DestinationListAdapter(Context context) {
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

        @SuppressLint("SimpleDateFormat")
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm a");
        destinationViewHolder.timestamp.setText(df.format(destination.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return mDestinations == null ? 0 : mDestinations.size();
    }

    void setDestinations(List<Destination> destinations) {
        mDestinations = destinations;
        notifyDataSetChanged();
    }

    Destination getDestinationAt(int index) {
        if (mDestinations == null || mDestinations.size() <= index) return null;

        return mDestinations.get(index);
    }

    class DestinationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.destinationTitle) TextView title;
        @BindView(R.id.destinationTimestamp) TextView timestamp;

        private DestinationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
