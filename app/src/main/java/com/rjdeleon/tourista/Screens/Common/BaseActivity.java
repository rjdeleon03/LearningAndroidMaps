package com.rjdeleon.tourista.Screens.Common;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.rjdeleon.tourista.Application.TouristaApp;
import com.rjdeleon.tourista.Common.DependencyInjection.ApplicationComponent;
import com.rjdeleon.tourista.Common.DependencyInjection.PresentationComponent;
import com.rjdeleon.tourista.Common.DependencyInjection.PresentationModule;

public class BaseActivity extends AppCompatActivity {

    public ApplicationComponent getApplicationComponent() {
        return ((TouristaApp) getApplication()).getApplicationComponent();
    }

    public PresentationComponent getPresentationComponent() {
        return getApplicationComponent()
                .newPresentationComponent(new PresentationModule(LayoutInflater.from(this)));
    }
}
