package com.rjdeleon.tourista.core.base;

import android.content.Context;
import android.content.DialogInterface;

import com.rjdeleon.tourista.R;

import androidx.appcompat.app.AlertDialog;

public class DialogManager {

    public static void createAndShowDialog(Context context, int messageId,
                                           DialogInterface.OnClickListener positiveButtonListener,
                                           DialogInterface.OnClickListener negativeButtonListener) {
        new AlertDialog.Builder(context)
                .setMessage(messageId)
                .setPositiveButton(R.string.text_ok, positiveButtonListener)
                .setNegativeButton(R.string.text_cancel, negativeButtonListener)
                .show();
    }
}
