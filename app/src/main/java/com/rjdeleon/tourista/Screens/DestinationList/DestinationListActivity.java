package com.rjdeleon.tourista.Screens.DestinationList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.rjdeleon.tourista.Data.Destination;
import com.rjdeleon.tourista.MainActivity;
import com.rjdeleon.tourista.Screens.Common.BaseActivity;

import java.util.List;

public class DestinationListActivity extends BaseActivity
        implements DestinationListViewMvc.Listener, FetchDestinationsListUseCase.Listener {

    // DI
    private DestinationListViewMvc _viewMvc;
    private FetchDestinationsListUseCase _fetchDestinationsListUc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _viewMvc = new DestinationListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(_viewMvc.getRootView());

        _fetchDestinationsListUc = getCompositionRoot().getFetchDestinationsListUseCase();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getDestinationsList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        _viewMvc.registerListener(this);
        _fetchDestinationsListUc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        _viewMvc.unregisterListener(this);
        _fetchDestinationsListUc.unregisterListener(this);
    }

    private void getDestinationsList() {
        _fetchDestinationsListUc.fetchAllDestinations();
    }

    @Override
    public void onAddDestinationClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onFetchOfAllDestinations(List<Destination> destinations) {
        _viewMvc.bindDestinations(destinations);
    }
}
