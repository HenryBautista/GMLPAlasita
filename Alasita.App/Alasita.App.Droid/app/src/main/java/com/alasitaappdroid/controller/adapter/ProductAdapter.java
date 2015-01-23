package com.alasitaappdroid.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alasitaappdroid.R;
import com.alasitaappdroid.model.Product;

import java.util.ArrayList;

/**
 * Created by Arun on 01/16/2015.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    public ProductAdapter(Context context, ArrayList<Product> objects) {
        super(context, R.layout.adapter_product, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.adapter_product, null);
        } else {
            v = convertView;
        }
        TextView textProductName = (TextView) v.findViewById(R.id.text_adapter_product_name);
        textProductName.setText(getItem(position).getProductName());


        return v;
    }
}
