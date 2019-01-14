package com.rjdeleon.tourista.feature.tripIndiv;

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

public class TripIndivDestinationsAdapter
        extends RecyclerView.Adapter<TripIndivDestinationsAdapter.TripIndivDestinationsViewHolder> {

    private List<Destination> mDestinations;
    private LayoutInflater mInflater;

    public TripIndivDestinationsAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TripIndivDestinationsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TripIndivDestinationsViewHolder(
                mInflater.inflate(R.layout.item_destination, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TripIndivDestinationsViewHolder tripIndivDestinationsViewHolder, int i) {
        Destination destination = mDestinations.get(i);
        tripIndivDestinationsViewHolder.destinationNameText.setText(destination.getNotes());
    }

    @Override
    public int getItemCount() {
        return mDestinations == null ? 0 : mDestinations.size();
    }

    public void setDestinations(List<Destination> destinations) {
        mDestinations = destinations;
        notifyDataSetChanged();
    }

    class TripIndivDestinationsViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        TextView destinationNameText;

        public TripIndivDestinationsViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            destinationNameText = mItemView.findViewById(R.id.destinationNameText);
        }

        void setItemClickListener(View.OnClickListener listener) {
            mItemView.setOnClickListener(listener);
        }
    }
}