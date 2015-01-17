package com.alasitaappdroid.controller.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
    private Button mButtonHideFragment;

    private Sector mCurrentSector;

    public static final String ASSOCIATION_NAME = "association";

    public SectorFragment() {
        mCurrentSector = new Sector("Artesanias", "Sector 01", "Aca se encuentran artesanias de todo tipo.", "000");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sector, container, false);
        mTextName = (TextView) v.findViewById(R.id.text_sector_name);
        mTextKey = (TextView) v.findViewById(R.id.text_sector_key);
        mTextDescription = (TextView) v.findViewById(R.id.text_sector_description);
        mTextAssociations = (TextView) v.findViewById(R.id.text_sector_associations);
        mListAssociations = (ListView) v.findViewById(R.id.list_associations);
        mButtonHideFragment = (Button) v.findViewById(R.id.button_hide_fragment);

        mTextName.setText(mCurrentSector.getSectorName());
        mTextKey.setText(mCurrentSector.getSectorKey());
        mTextDescription.setText(mCurrentSector.getSectorDescription());
        mTextAssociations.setText("Asociaciones:");

        AssociationAdapter adapter = new AssociationAdapter(getActivity(), R.layout.adapter_association, mCurrentSector.getSectorAssociations());
        mListAssociations.setAdapter(adapter);


        mButtonHideFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MapActivity) getActivity()).hideFragment();
            }
        });

        mListAssociations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO get Association NAME from the view, add it to the Intent and Start AssociationActivity
                //String currentName = ((Association) ((ListView) view).getItemAtPosition(position)).getAssociationName();
                String currentName = parent.getItemAtPosition(position).toString();
                // dummy content
                currentName = "dummy 1";
                Intent intent = new Intent(getActivity(), AssociationActivity.class);
                intent.putExtra(ASSOCIATION_NAME, currentName);
                startActivity(intent);
            }
        });
        return v;
    }
}
