package com.rjdeleon.tourista.Screens.DestinationList;

import android.support.annotation.UiThread;

import com.rjdeleon.tourista.Data.Destination;
import com.rjdeleon.tourista.Screens.Common.ObservableViewMvc;

import java.util.List;

public interface DestinationListViewMvc extends ObservableViewMvc<DestinationListViewMvc.Listener> {

    interface Listener {
        void onAddDestinationClicked();
    }

    void bindDestinations(List<Destination> destinations);
}
