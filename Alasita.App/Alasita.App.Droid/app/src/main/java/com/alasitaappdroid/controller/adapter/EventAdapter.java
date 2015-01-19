package com.alasitaappdroid.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alasitaappdroid.R;
import com.alasitaappdroid.model.Event;

import java.util.ArrayList;

/**
 * Created by Arun on 01/19/2015.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    private TextView mTextEventDate;
    private TextView mTextEventActivity;
    private TextView mTextEventPlace;
    private TextView mTextEventTime;

    public EventAdapter(Context context, int resource, ArrayList<Event> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.adapter_schedule, null);
        } else {
            v = convertView;
        }

        mTextEventActivity = (TextView) v.findViewById(R.id.text_adapter_schedule_activity);
        mTextEventDate = (TextView) v.findViewById(R.id.text_adapter_schedule_date);
        mTextEventPlace = (TextView) v.findViewById(R.id.text_adapter_schedule_place);
        mTextEventTime = (TextView) v.findViewById(R.id.text_adapter_schedule_time);

        mTextEventActivity.setText(getItem(position).getEventActivity());
        mTextEventDate.setText(getItem(position).getEventDate());
        mTextEventPlace.setText(getItem(position).getEventPlace());
        mTextEventTime.setText(getItem(position).getEventTime());

        return v;
    }
}

