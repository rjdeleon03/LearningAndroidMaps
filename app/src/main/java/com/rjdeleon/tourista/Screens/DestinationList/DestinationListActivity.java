package com.rjdeleon.tourista.Screens.DestinationList;

import android.content.Intent;
import android.os.Bundle;

import com.rjdeleon.tourista.Common.DependencyInjection.DaggerPresentationComponent;
import com.rjdeleon.tourista.Data.Destination;
import com.rjdeleon.tourista.MainActivity;
import com.rjdeleon.tourista.Screens.Common.BaseActivity;
import com.rjdeleon.tourista.Screens.Common.ViewMvcFactory;

import java.util.List;

import javax.inject.Inject;

public class DestinationListActivity extends BaseActivity
        implements DestinationListViewMvc.Listener, FetchDestinationsListUseCase.Listener {

    // DI

    @Inject
    ViewMvcFactory viewMvcFactory;

    @Inject
    public FetchDestinationsListUseCase fetchDestinationsListUc;

    private DestinationListViewMvc _viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPresentationComponent().inject(this);

        _viewMvc = viewMvcFactory
                .newInstance(DestinationListViewMvc.class, null);
        setContentView(_viewMvc.getRootView());

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
        fetchDestinationsListUc.registerListener(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        _viewMvc.unregisterListener(this);
        fetchDestinationsListUc.unregisterListener(this);
    }

    private void getDestinationsList() {
        fetchDestinationsListUc.fetchAllDestinations();
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
