package com.alasitaappdroid.controller.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.alasitaappdroid.AssociationActivity;
import com.alasitaappdroid.R;
import com.alasitaappdroid.controller.adapter.AssociationAdapter;
import com.alasitaappdroid.model.Sector;

/**
 * Created by Arun on 01/13/2015.
 */
public class SectorFragment extends Fragment {

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
        TextView textName = (TextView) v.findViewById(R.id.text_sector_name);
        TextView textKey = (TextView) v.findViewById(R.id.text_sector_key);
        TextView textDescription = (TextView) v.findViewById(R.id.text_sector_description);
        TextView textAssociations = (TextView) v.findViewById(R.id.text_sector_associations);
        ListView listAssociations = (ListView) v.findViewById(R.id.list_associations);

        textKey.setBackgroundColor(getSectorColor());
        textKey.setText("Sector " + mCurrentSector.getSectorKey());
        textName.setText("Sector " + mCurrentSector.getSectorName());
        textDescription.setText(mCurrentSector.getSectorDescription());
        textAssociations.setText("Asociaciones:");

        AssociationAdapter adapter = new AssociationAdapter(getActivity(), mCurrentSector.getSectorAssociations());
        listAssociations.setAdapter(adapter);


        listAssociations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    void hideThisFragment() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    private int getSectorColor() {
        int color = 0;
        if (mCurrentSector != null) {
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
                    color = Color.argb(255, 255, 88, 0);
                    break;
                case "E":
                    color = Color.argb(255, 216, 255, 79);
                    break;
                case "F":
                    color = Color.argb(255, 0, 255, 28);
                    break;
                case "G":
                    color = Color.argb(255, 255, 204, 43);
                    break;
                case "H":
                    color = Color.argb(255, 21, 181, 229);
                    break;
                case "K":
                    color = Color.argb(255, 135, 251, 11);
                    break;
                case "N":
                    color = Color.argb(255, 146, 241, 255);
                    break;
                case "R":
                    color = Color.argb(255, 255, 102, 115);
                    break;
                default:
                    color = Color.argb(255, 21, 181, 229);
                    break;
            }
        }
        return color;
    }

}
