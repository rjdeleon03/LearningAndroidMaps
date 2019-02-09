package com.rjdeleon.tourista.core.common;

import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.rjdeleon.tourista.Constants;
import com.rjdeleon.tourista.R;

import org.joda.time.DateTime;

import java.util.TimeZone;

import androidx.databinding.BindingAdapter;

public class BindingAdapters {

    @BindingAdapter("placeText")
    public static void setPlaceText(AutoCompleteTextView view, String placeName) {
        if (placeName == null) return;
        view.setText(placeName);
        view.clearFocus();
    }

    @BindingAdapter("enabled")
    public static void setEnabledState(View view, boolean isEnabled) {
        view.setClickable(isEnabled);
        view.setFocusable(isEnabled);
    }

    @BindingAdapter("dateText")
    public static void setDateText(TextView view, DateTime dateTime) {
        if (dateTime == null) {
            view.setText(R.string.text_loading);
            return;
        }
        view.setText(dateTime.toString(Constants.DATE_FORMAT));
    }

    @BindingAdapter("timeText")
    public static void setTimeText(TextView view, DateTime dateTime) {
        if (dateTime == null) {
            view.setText(R.string.text_loading);
            return;
        }
        view.setText(dateTime.toString(Constants.TIME_FORMAT));
    }

    @BindingAdapter("timeZoneText")
    public static void setTimeZoneText(TextView view, String timeZoneId) {
        if (timeZoneId == null) {
            view.setText(R.string.text_loading);
            return;
        }
        view.setText(TimeZone.getTimeZone(timeZoneId).getDisplayName());
    }
}
