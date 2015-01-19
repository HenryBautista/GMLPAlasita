package com.alasitaappdroid.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alasitaappdroid.AssociationActivity;
import com.alasitaappdroid.MapActivity;
import com.alasitaappdroid.R;
import com.alasitaappdroid.controller.adapter.AssociationAdapter;
import com.alasitaappdroid.model.Association;
import com.alasitaappdroid.model.Sector;

/**
 * Created by Arun on 01/13/2015.
 */
public class SectorFragment extends Fragment {

    private TextView mTextName;
    private TextView mTextKey;
    private TextView mTextDescription;
    private TextView mTextAssociations;
    private ListView mListAssociations;



    private Sector mCurrentSector;

    public static final String ASSOCIATION_NAME = "association";

    public SectorFragment() {
    }

    public void setCurrentSector(Sector currentSector) {
        mCurrentSector = currentSector;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //mCurrentSector = ((MapActivity) getActivity()).mCurrentSector;
        View v;
        if (getView() == null) {
            v = inflater.inflate(R.layout.fragment_sector, container, false);
        } else {
            v = getView();
        }
        mTextName = (TextView) v.findViewById(R.id.text_sector_name);
        mTextKey = (TextView) v.findViewById(R.id.text_sector_key);
        mTextDescription = (TextView) v.findViewById(R.id.text_sector_description);
        mTextAssociations = (TextView) v.findViewById(R.id.text_sector_associations);
        mListAssociations = (ListView) v.findViewById(R.id.list_associations);

        mTextName.setText(mCurrentSector.getSectorName());
        mTextKey.setText("Sector " + mCurrentSector.getSectorKey());
        mTextDescription.setText(mCurrentSector.getSectorDescription());
        mTextAssociations.setText("Asociaciones:");

        AssociationAdapter adapter = new AssociationAdapter(getActivity(), R.layout.adapter_association, mCurrentSector.getSectorAssociations());
        mListAssociations.setAdapter(adapter);


        mListAssociations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currentName = parent.getItemAtPosition(position).toString();
                Intent intent = new Intent(getActivity(), AssociationActivity.class);
                intent.putExtra(ASSOCIATION_NAME, currentName);
                startActivity(intent);
            }
        });
        return v;
    }

    private void hideThisFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, null);
    }


}
