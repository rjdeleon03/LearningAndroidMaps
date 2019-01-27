package com.rjdeleon.tourista.core.base;

import android.content.DialogInterface;

import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment {

    public interface DismissListener {
        void onDismiss();
    }

    private DismissListener mDismissListener;

    public void setDismissListener(DismissListener dismissListener) {
        mDismissListener = dismissListener;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        if (mDismissListener == null) return;
        mDismissListener.onDismiss();
    }
}
