package com.rjdeleon.tourista.Common.DependencyInjection;

import com.rjdeleon.tourista.Screens.DestinationList.DestinationListActivity;

import dagger.Component;
import dagger.Subcomponent;

@PresentationScope
@Subcomponent(modules = {PresentationModule.class})
public interface PresentationComponent {

    void inject(DestinationListActivity destinationListActivity);
}
