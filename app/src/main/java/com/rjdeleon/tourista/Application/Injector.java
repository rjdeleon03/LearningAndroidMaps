package com.rjdeleon.tourista.Application;

import com.rjdeleon.tourista.Screens.DestinationList.DestinationListActivity;

public class Injector {

    private PresentationCompositionRoot _presentationCompositionRoot;

    public Injector(PresentationCompositionRoot presentationCompositionRoot) {
        _presentationCompositionRoot = presentationCompositionRoot;
    }

    public void inject(Object client) {
        if (client instanceof DestinationListActivity) {
            injectDestinationListActivity((DestinationListActivity) client);
        } else {
            throw new RuntimeException("Invalid client!");
        }
    }

    private void injectDestinationListActivity(DestinationListActivity client) {
        client.viewMvcFactory = _presentationCompositionRoot.getViewMvcFactory();
        client.fetchDestinationsListUc = _presentationCompositionRoot.getFetchDestinationsListUseCase();
    }
}
