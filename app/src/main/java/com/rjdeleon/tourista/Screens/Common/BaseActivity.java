package com.rjdeleon.tourista.Screens.Common;

import android.support.annotation.UiThread;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.rjdeleon.tourista.Application.CompositionRoot;
import com.rjdeleon.tourista.Application.Injector;
import com.rjdeleon.tourista.Application.PresentationCompositionRoot;
import com.rjdeleon.tourista.Application.TouristaApp;

public class BaseActivity extends AppCompatActivity {

    private PresentationCompositionRoot _presentationCompositionRoot;
    private Injector _injector;

    @UiThread
    protected PresentationCompositionRoot getCompositionRoot() {
        if (_presentationCompositionRoot == null) {
            _presentationCompositionRoot = new PresentationCompositionRoot(
                    getAppCompositionRoot(),
                    LayoutInflater.from(this));
        }
        return _presentationCompositionRoot;
    }

    public CompositionRoot getAppCompositionRoot() {
        return ((TouristaApp) getApplication()).getCompositionRoot();
    }

    @UiThread
    public Injector getInjector() {
        if (_injector == null) {
            _injector = new Injector(getCompositionRoot());
        }
        return _injector;
    }
}
