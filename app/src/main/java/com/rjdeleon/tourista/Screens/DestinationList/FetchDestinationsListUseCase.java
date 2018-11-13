package com.rjdeleon.tourista.Screens.DestinationList;

import com.rjdeleon.tourista.Common.BaseObservable;
import com.rjdeleon.tourista.Data.DaoAccess;
import com.rjdeleon.tourista.Data.Destination;

import java.util.List;

public class FetchDestinationsListUseCase extends BaseObservable<FetchDestinationsListUseCase.Listener> {

    public interface Listener {
        void onFetchOfAllDestinations(List<Destination> destinations);
    }

    // Database access object
    private DaoAccess _daoAccess;


    public FetchDestinationsListUseCase(DaoAccess daoAccess) {

        _daoAccess = daoAccess;
    }

    public void fetchAllDestinations() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Destination> destinations = _daoAccess.getAllDestinations();
                for(Listener listener : getListeners()) {
                    listener.onFetchOfAllDestinations(destinations);
                }
            }
        }).start();
    }
}
