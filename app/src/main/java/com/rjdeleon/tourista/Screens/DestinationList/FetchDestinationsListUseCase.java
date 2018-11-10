package com.rjdeleon.tourista.Screens.DestinationList;

import com.rjdeleon.tourista.Common.BaseObservable;
import com.rjdeleon.tourista.Data.Destination;
import com.rjdeleon.tourista.Data.DestinationDatabase;
import com.rjdeleon.tourista.TouristaApp;

import java.util.List;

public class FetchDestinationsListUseCase extends BaseObservable<FetchDestinationsListUseCase.Listener> {

    public interface Listener {
        void onFetchOfAllDestinations(List<Destination> destinations);
    }

    // Database
    private DestinationDatabase _destinationDatabase;


    public FetchDestinationsListUseCase(TouristaApp application) {

        _destinationDatabase = application.getDatabase();
    }

    public void fetchAllDestinations() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Destination> destinations = _destinationDatabase.daoAccess().getAllDestinations();
                for(Listener listener : getListeners()) {
                    listener.onFetchOfAllDestinations(destinations);
                }
            }
        });
    }
}
