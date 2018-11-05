package com.rjdeleon.tourista.destination;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.data.Destination;

import java.util.List;

public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestinationListViewHolder> {

    private List<Destination> _destinationList;

    @NonNull
    @Override
    public DestinationListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_destination_list, viewGroup, false);
        return new DestinationListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DestinationListViewHolder destinationListViewHolder, int i) {
        destinationListViewHolder.setDestinationName(
                _destinationList.get(i).getPlace()
        );
    }

    @Override
    public int getItemCount() {
        return _destinationList.size();
    }

    public void setDestinationList(List<Destination> destinationList) {
        _destinationList = destinationList;
    }

    public class DestinationListViewHolder extends RecyclerView.ViewHolder {

        private TextView _destNameField;

        public DestinationListViewHolder(@NonNull View itemView) {
            super(itemView);
            _destNameField = itemView.findViewById(R.id.destItemName);
        }

        public void setDestinationName(String destinationName) {
            _destNameField.setText(destinationName);
        }
    }
}
