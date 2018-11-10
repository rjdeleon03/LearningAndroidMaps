package com.rjdeleon.tourista.Screens.DestinationList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.rjdeleon.tourista.MainActivity;
import com.rjdeleon.tourista.TouristaApp;
import com.rjdeleon.tourista.Data.DestinationDatabase;

public class DestinationListActivity extends AppCompatActivity
        implements DestinationListViewMvc.Listener {

    // Database
    private DestinationDatabase _destinationDatabase;

    // DI
    private DestinationListViewMvc _viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _viewMvc = new DestinationListViewMvcImpl(LayoutInflater.from(this), null);
        setContentView(_viewMvc.getRootView());

        _destinationDatabase = ((TouristaApp) getApplication()).getDatabase();
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
    }

    @Override
    protected void onStop() {
        super.onStop();
        _viewMvc.unregisterListener(this);
    }

    private void getDestinationsList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                _viewMvc.bindDestinations(_destinationDatabase.daoAccess().getAllDestinations());
            }
        }).start();
    }

    @Override
    public void onAddDestinationClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //region Old methods

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //endregion
}
