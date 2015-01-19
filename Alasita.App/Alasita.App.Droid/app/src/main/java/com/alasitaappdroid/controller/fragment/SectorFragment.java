package com.alasitaappdroid.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
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
        View v = inflater.inflate(R.layout.fragment_sector, container, false);
        mTextName = (TextView) v.findViewById(R.id.text_sector_name);
        mTextKey = (TextView) v.findViewById(R.id.text_sector_key);
        mTextDescription = (TextView) v.findViewById(R.id.text_sector_description);
        mTextAssociations = (TextView) v.findViewById(R.id.text_sector_associations);
        mListAssociations = (ListView) v.findViewById(R.id.list_associations);

        mTextKey.setBackgroundColor(getSectorColor());
        mTextKey.setText("Sector " + mCurrentSector.getSectorKey());
        mTextName.setText("Sector " + mCurrentSector.getSectorName());
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
                hideThisFragment();
            }
        });
        return v;
    }

    public void hideThisFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private int getSectorColor() {
        int color = 0;
        switch (mCurrentSector.getSectorKey()) {
            case "A":
                color = Color.argb(255, 63, 84, 204);
                break;
            case "B":
                color = Color.argb(255, 255, 44, 44);
                break;
            case "C":
                color = Color.argb(255, 87, 79, 177);
                break;
            case "D":
                color = Color.argb(255, 255, 88, 00);
                break;
            case "E":
                color = Color.argb(255, 216, 255, 79);
                break;
            case "F":
                color = Color.argb(255, 00, 255, 28);
                break;
            case "G":
                color = Color.argb(255, 255, 204, 43);
                break;
            case "H":
                color = Color.argb(255, 21, 181, 229);
                break;
            default:
                color = Color.argb(255, 21, 181, 229);
                break;
        }
        return color;
    }

}
