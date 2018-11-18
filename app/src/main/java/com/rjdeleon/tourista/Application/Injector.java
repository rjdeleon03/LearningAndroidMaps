package com.rjdeleon.tourista.Application;

import com.rjdeleon.tourista.Common.DependencyInjection.PresentationComponent;
import com.rjdeleon.tourista.Screens.DestinationList.DestinationListActivity;

public class Injector {

    private PresentationComponent _presentationComponent;

    public Injector(PresentationComponent presentationComponent) {
        _presentationComponent = presentationComponent;
    }

    public void inject(Object client) {
        if (client instanceof DestinationListActivity) {
            injectDestinationListActivity((DestinationListActivity) client);
        } else {
            throw new RuntimeException("Invalid client!");
        }
    }

    private void injectDestinationListActivity(DestinationListActivity client) {
//        client.viewMvcFactory = _presentationComponent.getViewMvcFactory();
//        client.fetchDestinationsListUc = _presentationComponent.getFetchDestinationsListUseCase();
    }
}
