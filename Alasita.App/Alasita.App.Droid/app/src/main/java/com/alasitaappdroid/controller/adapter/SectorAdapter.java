package com.alasitaappdroid.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alasitaappdroid.R;
import com.alasitaappdroid.model.Sector;

import java.util.ArrayList;

/**
 * Created by Arun on 01/18/2015.
 */
public class SectorAdapter extends ArrayAdapter<Sector> {

    public SectorAdapter(Context context, ArrayList<Sector> objects) {
        super(context, R.layout.adapter_sector, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.adapter_sector, null);
        } else {
            v = convertView;
        }
        TextView textSectorName = (TextView) v.findViewById(R.id.text_adapter_sector_name);
        TextView textSectorDescription = (TextView) v.findViewById(R.id.text_adapter_sector_description);
        textSectorName.setText(getItem(position).getSectorName());
        textSectorDescription.setText(getItem(position).getSectorDescription());
        return v;
    }

}
