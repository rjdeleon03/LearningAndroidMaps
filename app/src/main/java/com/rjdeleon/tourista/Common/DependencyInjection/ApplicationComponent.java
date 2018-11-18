package com.rjdeleon.tourista.Common.DependencyInjection;

import com.rjdeleon.tourista.Screens.DestinationList.FetchDestinationsListUseCase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    public FetchDestinationsListUseCase getFetchDestinationsListUseCase();
}
