package com.rjdeleon.tourista.Application;

import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

public class PresentationCompositionRoot {

    private final CompositionRoot _appCompositionRoot;

    public PresentationCompositionRoot(CompositionRoot appCompositionRoot) {

        _appCompositionRoot = appCompositionRoot;
    }

    public FetchDestinationsListUseCase getFetchDestinationsListUseCase() {
        return _appCompositionRoot.getFetchDestinationsListUseCase();
    }
}
