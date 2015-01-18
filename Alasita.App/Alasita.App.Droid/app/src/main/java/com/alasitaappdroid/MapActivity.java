package com.alasitaappdroid;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.alasitaappdroid.controller.fragment.SectorFragment;
import com.alasitaappdroid.model.Association;
import com.alasitaappdroid.model.Product;
import com.alasitaappdroid.model.Sector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MapActivity extends ActionBarActivity {

    private ImageView mImageMap;
    private FrameLayout mFrameContainer;
    private boolean mTap;
    private MenuItem mBackMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        try {
            loadSectors();
        } catch (Exception e) {
            Log.d("JSON Error", e.getMessage());
        }
        mImageMap = (ImageView) findViewById(R.id.image_map);
        mFrameContainer = (FrameLayout) findViewById(R.id.container);

        mImageMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mTap = true;
                }
                if (event.getAction() == MotionEvent.ACTION_UP && mTap == true) {
                    mTap = false;
                    Toast.makeText(getApplicationContext(), event.getX() + " " + event.getY(), Toast.LENGTH_SHORT).show();
                    //TODO Implement getPixel()
                    hideMap(v);
                    InflateFragment();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mTap = false;
                }
                return false;
            }
        });
    }


    private void hideMap(View v) {
        try {
            if (Build.VERSION.SDK_INT > 11) {
                v.setAlpha(0.2f);
            } else {
                v.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            Log.d("Debug Error", e.getMessage());
        }
    }

    private void InflateFragment() {
        mImageMap.setClickable(false);
        mBackMenu.setVisible(true);
        mFrameContainer.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SectorFragment()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        mBackMenu = menu.getItem(0);
        mBackMenu.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_back) {
            hideFragment();
            mBackMenu.setVisible(false);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void hideFragment() {
        mImageMap.setClickable(true);

        mFrameContainer.setVisibility(View.INVISIBLE);
        try {
            if (Build.VERSION.SDK_INT > 11) {
                mImageMap.setAlpha(1f);
            } else {
                mImageMap.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Log.d("Debug Error", e.getMessage());
        }
    }

    public String loadJSON() {
        String json = "";
        try {
            InputStream inputStream = getAssets().open("carnival.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (Exception e) {
            Log.d("Json Error", e.getMessage());
            return null;
        }
        return json;
    }

    public void loadSectors() throws JSONException {
        JSONObject jsonObject = new JSONObject(loadJSON());

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
            Association association = new Association();
            for (int j = 0; j < associationArray.length(); j++) {
                JSONObject jAssociation = associationArray.getJSONObject(j);
                String AssociationName = jAssociation.getString("AssociationName");
                int AssociationKey = jAssociation.getInt("AssociationKey");
                String AssociationDescription = jAssociation.getString("AssociationDescription");
                String AssociationImage = jAssociation.getString("AssociationImage");
                int ExpoNumber = jAssociation.getInt("ExpoNumber");

                JSONArray InfoArray = jAssociation.getJSONArray("AssociationInfo");
                ArrayList<String> InfoList = new ArrayList<>();
                for (int k = 0; k < InfoArray.length(); k++) {
                    String Info = InfoArray.getString(k);
                    InfoList.add(Info);
                }

                JSONArray ProductArray = jAssociation.getJSONArray("AssociationProducts");
                ArrayList<Product> ProductList = new ArrayList<>();
                Product product = new Product();
                for (int k = 0; k < ProductArray.length(); k++) {
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
                association.setExpoNumber(ExpoNumber);
                association.setAssociationInfo(InfoList);
                association.setAssociationProducts(ProductList);
                AssociationList.add(association);
            }
            sector.setSectorAssociations(AssociationList);
            sector.setSectorName(SectorName);
            sector.setSectorKey(SectorKey);
            sector.setSectorDescription(SectorDescription);
            sector.setSectorMapImage(SectorMapImage);
            SectorList.add(i, sector);
            Log.d("as", i + "");
        }
    }
}
