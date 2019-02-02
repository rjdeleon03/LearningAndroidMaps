package com.rjdeleon.tourista.old.timeZonePicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rjdeleon.tourista.R;

import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeZonePickerAdapter extends RecyclerView.Adapter<TimeZonePickerAdapter.TimeZonePickerViewHolder> {

    private List<String> mTimeZones;
    private LayoutInflater mInflater;

    public TimeZonePickerAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TimeZonePickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_time_zone, parent, false);
        return new TimeZonePickerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeZonePickerViewHolder holder, int position) {
        TimeZone tz = TimeZone.getTimeZone(mTimeZones.get(position));
        holder.timeZoneDisplayNameText.setText(tz.getDisplayName());
        holder.timeZoneOffsetText.setText("Sample Offset");
    }

    @Override
    public int getItemCount() {
        return mTimeZones == null ? 0 : mTimeZones.size();
    }

    public void setTimeZones(String[] timeZones) {
        mTimeZones = Arrays.asList(timeZones);
        notifyDataSetChanged();
    }

    class TimeZonePickerViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;

        @BindView(R.id.timeZoneDisplayNameText)
        TextView timeZoneDisplayNameText;

        @BindView(R.id.timeZoneOffsetText)
        TextView timeZoneOffsetText;

        @BindView(R.id.timeZoneCountryText)
        TextView timeZoneCountryText;

        TimeZonePickerViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            ButterKnife.bind(this, mItemView);
        }

        void setItemClickListener(View.OnClickListener listener) {
            mItemView.setOnClickListener(listener);
        }
    }
}
