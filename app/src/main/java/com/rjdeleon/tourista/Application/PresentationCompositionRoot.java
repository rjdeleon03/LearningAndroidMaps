package com.rjdeleon.tourista.Application;

import android.view.LayoutInflater;

import com.rjdeleon.tourista.Screens.Common.ViewMvcFactory;
import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

public class PresentationCompositionRoot {

    private final CompositionRoot _appCompositionRoot;
    private final LayoutInflater _layoutInflater;

    public PresentationCompositionRoot(CompositionRoot appCompositionRoot,
                                       LayoutInflater layoutInflater) {

        _appCompositionRoot = appCompositionRoot;
        _layoutInflater = layoutInflater;
    }

    public FetchDestinationsListUseCase getFetchDestinationsListUseCase() {
        return _appCompositionRoot.getFetchDestinationsListUseCase();
    }

    public ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(_layoutInflater);
    }
}
