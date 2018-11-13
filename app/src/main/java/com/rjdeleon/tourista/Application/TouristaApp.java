package com.rjdeleon.tourista.Application;

import android.app.Application;

public class TouristaApp extends Application {

    private CompositionRoot _compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        getCompositionRoot();
    }

    public CompositionRoot getCompositionRoot() {
        if (_compositionRoot == null) {
            _compositionRoot = new CompositionRoot(getApplicationContext());
        }
        return _compositionRoot;
    }
}
