package com.rjdeleon.tourista.common;

import android.databinding.BindingAdapter;
import android.widget.TextView;

import org.joda.time.DateTime;

public class BindingAdapters {

    @BindingAdapter("dateText")
    public static void setDateText(TextView view, DateTime dateTime) {
        view.setText(dateTime.toString("yyyy/MM/dd"));
    }

    @BindingAdapter("timeText")
    public static void setTimeText(TextView view, DateTime dateTime) {
        view.setText(dateTime.toString("hh:mm"));
    }
}