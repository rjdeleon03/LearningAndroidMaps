package com.rjdeleon.tourista.old.tripIndiv;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TripIndivDestinationsAdapter
        extends RecyclerView.Adapter<TripIndivDestinationsAdapter.TripIndivDestinationsViewHolder> {

    private List<Destination> mDestinations;
    private LayoutInflater mInflater;

    TripIndivDestinationsAdapter(Context context) {
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
        tripIndivDestinationsViewHolder
                .setItemClickListener(createItemClickListener(destination.getId()));
    }

    @Override
    public int getItemCount() {
        return mDestinations == null ? 0 : mDestinations.size();
    }

    void setDestinations(List<Destination> destinations) {
        mDestinations = destinations;
        notifyDataSetChanged();
    }

    private View.OnClickListener createItemClickListener(final long id) {
        return view -> {
            TripIndivFragmentDirections.ActionTripIndivFragmentToDestinationFragment action =
                    TripIndivFragmentDirections.actionTripIndivFragmentToDestinationFragment();
            action.setId(id);
            Navigation.findNavController(view).navigate(action);
        };
    }

    class TripIndivDestinationsViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        @BindView(R.id.destinationNameText) TextView destinationNameText;

        TripIndivDestinationsViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.bind(this, mItemView);
        }

        void setItemClickListener(View.OnClickListener listener) {
            mItemView.setOnClickListener(listener);
        }
    }
}