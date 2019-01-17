package com.rjdeleon.tourista.core.base;

import android.view.WindowManager;

import java.util.Objects;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    protected void hideKeyboard()
    {
        Objects.requireNonNull(getActivity())
                .getWindow()
                .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
