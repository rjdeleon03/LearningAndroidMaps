package com.rjdeleon.tourista.Common.DependencyInjection;

import android.view.LayoutInflater;

import com.rjdeleon.tourista.Screens.Common.ViewMvcFactory;
import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final ApplicationComponent _applicationComponent;
    private final LayoutInflater _layoutInflater;

    public PresentationModule(ApplicationComponent applicationComponent,
                              LayoutInflater layoutInflater) {

        _applicationComponent = applicationComponent;
        _layoutInflater = layoutInflater;
    }

    @Provides
    FetchDestinationsListUseCase getFetchDestinationsListUseCase() {
        return _applicationComponent.getFetchDestinationsListUseCase();
    }

    @Provides
    ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(_layoutInflater);
    }
}
