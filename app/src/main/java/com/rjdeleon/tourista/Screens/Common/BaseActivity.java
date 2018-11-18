package com.rjdeleon.tourista.Screens.Common;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.rjdeleon.tourista.Application.Injector;
import com.rjdeleon.tourista.Application.TouristaApp;
import com.rjdeleon.tourista.Common.DependencyInjection.ApplicationComponent;
import com.rjdeleon.tourista.Common.DependencyInjection.DaggerPresentationComponent;
import com.rjdeleon.tourista.Common.DependencyInjection.PresentationComponent;
import com.rjdeleon.tourista.Common.DependencyInjection.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    private Injector _injector;

    public ApplicationComponent getApplicationComponent() {
        return ((TouristaApp) getApplication()).getApplicationComponent();
    }

    public PresentationComponent getPresentationComponent() {
        return DaggerPresentationComponent.builder()
                .presentationModule(new PresentationModule(getApplicationComponent(), LayoutInflater.from(this)))
                .build();
    }
}
