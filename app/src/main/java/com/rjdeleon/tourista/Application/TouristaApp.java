package com.rjdeleon.tourista.Application;

import android.app.Application;

public class TouristaApp extends Application {

    private CompositionRoot _compositionRoot;

    @Override
    public void onCreate() {
        super.onCreate();
        _compositionRoot = _compositionRoot;
    }

    public CompositionRoot getCompositionRoot() {
        return _compositionRoot;
    }
}
