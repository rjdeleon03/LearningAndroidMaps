package com.rjdeleon.tourista.Screens.DestinationList;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.Data.Destination;
import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.Screens.Common.BaseViewMvc;

import java.util.ArrayList;
import java.util.List;

public class DestinationListViewMvcImpl extends BaseViewMvc<DestinationListViewMvc.Listener>
        implements DestinationListViewMvc {

    private RecyclerView _destinationList;
    private DestinationListAdapter _adapter;

    public DestinationListViewMvcImpl(LayoutInflater inflater, ViewGroup container) {

        setRootView(inflater.inflate(R.layout.activity_destination_list, container, false));

        _destinationList = findViewById(R.id.destinationList);
        _destinationList.setLayoutManager(new LinearLayoutManager(getContext()));

        _adapter = new DestinationListAdapter();
        _destinationList.setAdapter(_adapter);
    }

    @Override
    public void bindDestinations(List<Destination> destinations) {
        _adapter.bindData(destinations);
    }

    public class DestinationListAdapter extends RecyclerView.Adapter<DestinationListAdapter.DestinationListViewHolder> {

        private List<Destination> _destinationList = new ArrayList<>(0);

        public class DestinationListViewHolder extends RecyclerView.ViewHolder {

            public TextView destNameField;

            public DestinationListViewHolder(@NonNull View itemView) {
                super(itemView);
                destNameField = itemView.findViewById(R.id.destItemName);
            }
        }

        public void bindData (List<Destination> destinationList) {
            _destinationList = new ArrayList<>(destinationList);

            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    notifyDataSetChanged();
                }
            });
        }

        @NonNull
        @Override
        public DestinationListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            final View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_destination_list, viewGroup, false);
            return new DestinationListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DestinationListViewHolder destinationListViewHolder, int i) {
            destinationListViewHolder.destNameField.setText(
                    _destinationList.get(i).getPlace()
            );
        }

        @Override
        public int getItemCount() {
            return _destinationList.size();
        }

        //region Old methods
        public void setDestinationList(List<Destination> destinationList) {
            _destinationList = destinationList;
        }

        //endregion
    }
}
