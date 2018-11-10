package com.rjdeleon.tourista.Screens.Common;

import android.support.v7.app.AppCompatActivity;

import com.rjdeleon.tourista.Screens.Application.CompositionRoot;
import com.rjdeleon.tourista.Screens.Application.TouristaApp;

public class BaseActivity extends AppCompatActivity {

    public CompositionRoot getCompositionRoot() {
        return ((TouristaApp) getApplication()).getCompositionRoot();
    }
}
