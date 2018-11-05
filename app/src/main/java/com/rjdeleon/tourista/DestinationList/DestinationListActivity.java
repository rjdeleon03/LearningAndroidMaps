package com.rjdeleon.tourista.DestinationList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.rjdeleon.tourista.MainActivity;
import com.rjdeleon.tourista.R;
import com.rjdeleon.tourista.TouristaApp;
import com.rjdeleon.tourista.Data.DestinationDatabase;

public class DestinationListActivity extends AppCompatActivity {

    private RecyclerView _destinationList;
    private DestinationDatabase _destinationDatabase;
    private DestinationListAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_list);

        _destinationDatabase = ((TouristaApp) getApplication()).getDatabase();
        _destinationList = findViewById(R.id.destinationList);
        _destinationList.setLayoutManager(new LinearLayoutManager(this));

        _adapter = new DestinationListAdapter();
        _destinationList.setAdapter(_adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDestinationsList();
    }

    private void getDestinationsList() {
        final DestinationListActivity finalSelf = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                _adapter.setDestinationList(_destinationDatabase.daoAccess().getAllDestinations());
                finalSelf.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        _adapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    public void onAddButtonClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
