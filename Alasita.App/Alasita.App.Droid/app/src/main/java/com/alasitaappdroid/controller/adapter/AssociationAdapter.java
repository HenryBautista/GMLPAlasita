package com.alasitaappdroid.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alasitaappdroid.R;
import com.alasitaappdroid.model.Association;

import java.util.ArrayList;

/**
 * Created by Arun on 01/14/2015.
 */
public class AssociationAdapter extends ArrayAdapter<Association> {

    private TextView mTextName;
    private TextView mTextDescription;

    public AssociationAdapter(Context context, int resource, ArrayList<Association> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.adapter_association, null);
        } else {
            v = convertView;
        }

        mTextName = (TextView) v.findViewById(R.id.text_adapter_association_name);
        mTextDescription = (TextView) v.findViewById(R.id.text_adapter_association_description);

        mTextName.setText(getItem(position).getAssociationName());
        mTextDescription.setText(getItem(position).getAssociationDescription());

        return v;
    }


}
