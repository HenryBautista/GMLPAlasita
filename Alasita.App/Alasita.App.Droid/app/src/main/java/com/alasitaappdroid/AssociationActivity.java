package com.alasitaappdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.alasitaappdroid.controller.adapter.ProductAdapter;
import com.alasitaappdroid.controller.fragment.SectorFragment;
import com.alasitaappdroid.model.Association;
import com.alasitaappdroid.model.Carnival;
import com.alasitaappdroid.model.Product;
import com.alasitaappdroid.model.Sector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class AssociationActivity extends ActionBarActivity {

    private TextView mTextAssociationName;
    private TextView mTextAssociationDescription;
    private ListView mListAssociationProducts;


    private Association mCurrentAssociation;
    private Carnival mCarnival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association);
        try {
            loadSectors();
        } catch (Exception e) {
            Log.d("JSON Error", e.getMessage());
        }
        Intent intent = getIntent();
        if (intent.getExtras().containsKey(SectorFragment.ASSOCIATION_NAME)) {
            findAssociation(intent.getExtras().getString(SectorFragment.ASSOCIATION_NAME));
        }
        getSupportActionBar().setTitle("Asociación " + mCurrentAssociation.getAssociationKey());

        mTextAssociationName = (TextView) findViewById(R.id.text_association_key);
        mTextAssociationDescription = (TextView) findViewById(R.id.text_association_description);
        mListAssociationProducts = (ListView) findViewById(R.id.list_products);
        ProductAdapter adapter = new ProductAdapter(this, R.layout.adapter_product, mCurrentAssociation.getAssociationProducts());
        mListAssociationProducts.setAdapter(adapter);

        mTextAssociationName.setText(mCurrentAssociation.getAssociationName());
        mTextAssociationDescription.setText(mCurrentAssociation.getAssociationDescription());
    }

    private void findAssociation(String name) {
        for (Sector sector : mCarnival.getCarnivalSectors()) {
            for (Association association : sector.getSectorAssociations()) {
                if (association.getAssociationName().equals(name)) {
                    mCurrentAssociation = association;
                    mCarnival = null;
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_association, menu);
        return true;
    }

    public String loadJSON() {
        String json;
        try {
            InputStream inputStream = getAssets().open("carnival.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer);     //tireishon aca
        } catch (Exception e) {
            Log.d("Json Error", e.getMessage());
            return null;
        }
        return json;
    }


    public void loadSectors() throws JSONException {
        String json = loadJSON();
        mCarnival = new Carnival();

        JSONObject jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));

        String CarnivalName = jsonObject.getString("CarnivalName");
        JSONArray array = jsonObject.getJSONArray("CarnivalSectors");
        ArrayList<Sector> SectorList = new ArrayList<Sector>();
        for (int i = 0; i < array.length(); i++) {
            Sector sector = new Sector();
            JSONObject jsector = array.getJSONObject(i);
            String SectorName = jsector.getString("SectorName");
            String SectorKey = jsector.getString("SectorKey");
            String SectorDescription = jsector.getString("SectorDescription");
            String SectorMapImage = jsector.getString("SectorMapImage");
            JSONArray associationArray = jsector.getJSONArray("SectorAssociations");
            ArrayList<Association> AssociationList = new ArrayList<>();
            JSONArray InfoArray = jsector.getJSONArray("Tags");
            ArrayList<String> TagList = new ArrayList<>();
            for (int k = 0; k < InfoArray.length(); k++) {
                String Tag = InfoArray.getString(k);
                TagList.add(Tag);
            }
            for (int j = 0; j < associationArray.length(); j++) {
                Association association = new Association();
                JSONObject jAssociation = associationArray.getJSONObject(j);
                String AssociationName = jAssociation.getString("AssociationName");
                int AssociationKey = jAssociation.getInt("AssociationKey");
                String AssociationDescription = jAssociation.getString("AssociationDescription");
                String AssociationImage = jAssociation.getString("AssociationImage");
                int ExpoNumber = jAssociation.getInt("ExpoNumber");

                JSONArray ProductArray = jAssociation.getJSONArray("AssociationProducts");
                ArrayList<Product> ProductList = new ArrayList<>();
                for (int k = 0; k < ProductArray.length(); k++) {
                    Product product = new Product();
                    JSONObject jProduct = ProductArray.getJSONObject(k);
                    String ProductName = jProduct.getString("ProductName");
                    String ProductImage = jProduct.getString("ProductImage");

                    product.setProductName(ProductName);
                    product.setProductImage(ProductImage);
                    ProductList.add(product);
                }

                association.setAssociationName(AssociationName);
                association.setAssociationKey(AssociationKey);
                association.setAssociationDescription(AssociationDescription);
                association.setAssociationImage(AssociationImage);
                association.setAssociationExpoNumber(ExpoNumber);
                association.setAssociationProducts(ProductList);
                AssociationList.add(association);
            }
            sector.setSectorTags(TagList);
            sector.setSectorAssociations(AssociationList);
            sector.setSectorName(SectorName);
            sector.setSectorKey(SectorKey);
            sector.setSectorDescription(SectorDescription);
            sector.setSectorMapImage(SectorMapImage);
            SectorList.add(i, sector);
        }
        mCarnival.setCarnivalName("CarnivalName");
        mCarnival.setCarnivalSectors(SectorList);
    }
}
