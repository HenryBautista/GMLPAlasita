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

    public EventAdapter(Context context, ArrayList<Event> objects) {
        super(context, R.layout.adapter_schedule, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.adapter_schedule, null);
        } else {
            v = convertView;
        }

        TextView textEventActivity = (TextView) v.findViewById(R.id.text_adapter_schedule_activity);
        TextView textEventDate = (TextView) v.findViewById(R.id.text_adapter_schedule_date);
        TextView textEventPlace = (TextView) v.findViewById(R.id.text_adapter_schedule_place);
        TextView textEventTime = (TextView) v.findViewById(R.id.text_adapter_schedule_time);

        textEventActivity.setText(getItem(position).getEventActivity());
        textEventDate.setText(getItem(position).getEventDate());
        textEventPlace.setText(getItem(position).getEventPlace());
        textEventTime.setText(getItem(position).getEventTime());

        return v;
    }
}

