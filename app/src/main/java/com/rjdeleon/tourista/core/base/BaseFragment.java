package com.rjdeleon.tourista.core.base;

import android.content.Context;
import android.hardware.input.InputManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class BaseFragment extends Fragment {

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    private void hideKeyboard()
    {
        FragmentActivity activity = Objects.requireNonNull(getActivity());
        InputMethodManager manager = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));

        assert manager != null;
        manager.hideSoftInputFromWindow(Objects
                .requireNonNull(activity.getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
