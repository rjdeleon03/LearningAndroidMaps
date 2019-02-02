package com.rjdeleon.tourista.core.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.ContextThemeWrapper;

import com.rjdeleon.tourista.R;

import androidx.appcompat.app.AlertDialog;

public class DialogManager {

    public static Dialog createConfirmationDialog(Context context, int messageId,
                                                  DialogInterface.OnClickListener positiveButtonListener,
                                                  DialogInterface.OnClickListener negativeButtonListener) {
        Dialog d = new AlertDialog.Builder(new ContextThemeWrapper(context, R.style.DialogTheme))
                .setMessage(messageId)
                .setPositiveButton(R.string.text_ok, positiveButtonListener)
                .setNegativeButton(R.string.text_cancel, negativeButtonListener)
                .create();

        if (d.getWindow() != null)
            d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return d;
    }
}
