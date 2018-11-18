package com.rjdeleon.tourista.Application;

import android.app.Application;

import com.rjdeleon.tourista.Common.DependencyInjection.ApplicationComponent;
import com.rjdeleon.tourista.Common.DependencyInjection.ApplicationModule;
import com.rjdeleon.tourista.Common.DependencyInjection.DaggerApplicationComponent;

public class TouristaApp extends Application {

    private ApplicationComponent _applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        _applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return _applicationComponent;
    }
}
