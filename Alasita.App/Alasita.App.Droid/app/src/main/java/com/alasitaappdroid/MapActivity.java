package com.alasitaappdroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

import com.alasitaappdroid.controller.fragment.SearchFragment;
import com.alasitaappdroid.controller.fragment.SectorFragment;
import com.alasitaappdroid.model.Association;
import com.alasitaappdroid.model.Carnival;
import com.alasitaappdroid.model.Product;
import com.alasitaappdroid.model.Sector;
import com.matabii.dev.scaleimageview.ScaleImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MapActivity extends ActionBarActivity {

    private ScaleImageView mImageMap;
    private FrameLayout mFrameContainer;

    private Carnival mCarnival;
    private Sector mCurrentSector;

    private static final int LEVEL = 20;

    private int mXPoint;
    private int mYPoint;
    private boolean mFragmentOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f58220")));
        try {
            loadSectors();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mFragmentOn = false;
        mImageMap = (ScaleImageView) findViewById(R.id.image_map);
        mFrameContainer = (FrameLayout) findViewById(R.id.container);

        mImageMap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mXPoint = (int) event.getX();
                    mYPoint = (int) event.getY();
                }

                if (event.getAction() == MotionEvent.ACTION_UP && !mFragmentOn) {
                    if (isInDragRange(event.getX(), event.getY())) {
                        int pixelColor = getColor((int) event.getX(), (int) event.getY());
                        int redValue = Color.red(pixelColor);
                        int greenValue = Color.green(pixelColor);
                        int blueValue = Color.blue(pixelColor);
                        String tappedSector = getTappedSector(redValue, greenValue, blueValue);
                        if (!tappedSector.equals("")) {
                            findSector(tappedSector);
                            InflateSectorFragment();
                        }
                    }
                }
                return false;
            }
        });
    }

    private boolean isInDragRange(float x, float y) {
        if (x >= mXPoint - LEVEL && x <= mXPoint + LEVEL) {
            if (y >= mYPoint - LEVEL && y <= mYPoint + LEVEL) {
                return true;
            }
        }
        return false;
    }

    private void findSector(String tappedSector) {
        for (Sector sector : mCarnival.getCarnivalSectors()) {
            if (sector.getSectorKey().equals(tappedSector)) {
                mCurrentSector = sector;
            }
        }
    }

    private String getTappedSector(int redValue, int greenValue, int blueValue) {
        String sectorKey;
        Log.d("Color", redValue + " " + greenValue + " " + blueValue);
        if (isInRange(redValue, 63) && isInRange(greenValue, 132) && isInRange(blueValue, 204)) {
            sectorKey = "A";
        } else if (isInRange(redValue, 255) && isInRange(greenValue, 68) && isInRange(blueValue, 68)) {
            sectorKey = "B";
        } else if (isInRange(redValue, 135) && isInRange(greenValue, 79) && isInRange(blueValue, 177)) {
            sectorKey = "C";
        } else if (isInRange(redValue, 255) && isInRange(greenValue, 136) && isInRange(blueValue, 0)) {
            sectorKey = "D";
        } else if (isInRange(redValue, 216) && isInRange(greenValue, 255) && isInRange(blueValue, 79)) {
            sectorKey = "E";
        } else if (isInRange(redValue, 0) && isInRange(greenValue, 255) && isInRange(blueValue, 40)) {
            sectorKey = "F";
        } else if (isInRange(redValue, 255) && isInRange(greenValue, 204) && isInRange(blueValue, 67)) {
            sectorKey = "G";
        } else if (isInRange(redValue, 33) && isInRange(greenValue, 181) && isInRange(blueValue, 229)) {
            sectorKey = "H";
        } else if (isInRange(redValue, 135) && isInRange(greenValue, 251) && isInRange(blueValue, 11)) {
            sectorKey = "K";
        } else if (isInRange(redValue, 146) && isInRange(greenValue, 241) && isInRange(blueValue, 255)) {
            sectorKey = "N";
        } else if (isInRange(redValue, 255) && isInRange(greenValue, 102) && isInRange(blueValue, 115)) {
            sectorKey = "R";
        } else sectorKey = "";

        return sectorKey;
    }

    private boolean isInRange(int color, int trueValue) {
        return color >= trueValue - 5 && color <= trueValue + 5;
    }

    @Override
    public void onBackPressed() {
        if (mFragmentOn) {
            hideFragment();
        } else {
            super.onBackPressed();
        }
    }

    private void hideMap() {
        mFragmentOn = true;
        mImageMap.setScaleType(ImageView.ScaleType.FIT_CENTER);
        try {
            if (Build.VERSION.SDK_INT > 11) {
                mImageMap.setAlpha(0.2f);
            } else {
                mImageMap.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            Log.d("Debug Error", e.getMessage());
        }
    }

    private void InflateSectorFragment() {
        hideMap();
        mFrameContainer.setVisibility(View.VISIBLE);
        SectorFragment sectorFragment = new SectorFragment();
        sectorFragment.setCurrentSector(mCurrentSector);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, sectorFragment).commit();
    }

    private void InflateSearchFragment() {
        hideMap();
        mFrameContainer.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new SearchFragment()).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_search) {
            InflateSearchFragment();
        } else if (id == R.id.menu_history)

        {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_schedule)

        {
            Intent intent = new Intent(getApplicationContext(), ScheduleActivity.class);
            startActivity(intent);
        } else if (id == R.id.menu_credit) {
            Intent intent = new Intent(this, CreditActivity.class);
            startActivity(intent);
        }

        return super.

                onOptionsItemSelected(item);

    }

    void hideFragment() {
        mFragmentOn = false;
        mFrameContainer.setVisibility(View.INVISIBLE);
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentById(R.id.container)).commit();
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

    String loadJSON() {
        String json;
        try {
            InputStream inputStream = getAssets().open("carnival.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer);
        } catch (Exception e) {
            Log.d("Json Error", e.getMessage());
            return null;
        }
        return json;
    }


    void loadSectors() throws JSONException {
        String json = loadJSON();
        mCarnival = new Carnival();

        JSONObject jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));

        String CarnivalName = jsonObject.getString("CarnivalName");
        JSONArray array = jsonObject.getJSONArray("CarnivalSectors");
        ArrayList<Sector> SectorList = new ArrayList<>();
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
        mCarnival.setCarnivalName(CarnivalName);
        mCarnival.setCarnivalSectors(SectorList);
    }

    private int getColor(int x, int y) {
        mImageMap.setDrawingCacheEnabled(true);
        Bitmap hotspot = Bitmap.createBitmap(mImageMap.getDrawingCache());
        mImageMap.setDrawingCacheEnabled(false);
        return hotspot.getPixel(x, y);
    }
}
