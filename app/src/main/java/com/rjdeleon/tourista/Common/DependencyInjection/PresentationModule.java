package com.rjdeleon.tourista.Common.DependencyInjection;

import android.view.LayoutInflater;

import com.rjdeleon.tourista.Screens.Common.ViewMvcFactory;
import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

import dagger.Module;
import dagger.Provides;

@Module
public class PresentationModule {

    private final LayoutInflater _layoutInflater;

    public PresentationModule(LayoutInflater layoutInflater) {

        _layoutInflater = layoutInflater;
    }

    @Provides
    ViewMvcFactory getViewMvcFactory() {
        return new ViewMvcFactory(_layoutInflater);
    }
}
