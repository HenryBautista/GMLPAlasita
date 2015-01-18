package com.alasitaappdroid.controller.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.alasitaappdroid.R;
import com.alasitaappdroid.model.Product;

/**
 * Created by Arun on 01/16/2015.
 */
public class ProductAdapter extends ArrayAdapter<Product> {

    private TextView mTextProductName;
    private TextView mTextProductKey;
    //private String mProductImage;
    private TextView mTextProductDescription;


    public ProductAdapter(Context context, int resource, Product[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null) {
            v = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.adapter_product, null);
        } else {
            v = convertView;
        }
        //TODO Implement view


        return v;
    }
}
