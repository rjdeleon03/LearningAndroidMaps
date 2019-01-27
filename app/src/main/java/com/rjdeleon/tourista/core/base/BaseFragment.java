package com.rjdeleon.tourista.core.base;

import android.content.Context;
import android.hardware.input.InputManager;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class BaseFragment extends Fragment {

    protected enum ToolbarVisibility {
        VISIBLE,
        GONE
    }

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    protected void hideKeyboard() {
        FragmentActivity activity = Objects.requireNonNull(getActivity());
        InputMethodManager manager = ((InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE));

        assert manager != null;
        try {
            manager.hideSoftInputFromWindow(Objects
                    .requireNonNull(activity.getCurrentFocus()).getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected void setToolbarVisibility(ToolbarVisibility visibility) {
        if (visibility == ToolbarVisibility.VISIBLE) {
            getActionBar().show();
        } else {
            getActionBar().hide();
        }
    }

    protected ActionBar getActionBar() {
        return ((AppCompatActivity) getActivity()).getSupportActionBar();
    }
}
