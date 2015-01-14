package com.alasitaappdroid.controller.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alasitaappdroid.R;

/**
 * Created by Arun on 01/13/2015.
 */
public class SectorFragment extends Fragment {
    public SectorFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sector, container, false);

        return v;
    }

}
