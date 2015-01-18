package com.alasitaappdroid;

import android.graphics.Bitmap;
import android.graphics.Color;
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

public class MapActivity extends ActionBarActivity {

    private ImageView mImageMap;
    private FrameLayout mFrameContainer;
    private boolean mTap;
    private MenuItem mBackMenu;

    private Carnival mCarnival;
    public Sector mCurrentSector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        try {
            loadSectors();
        } catch (Exception e) {

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
                    int pixelColor = getColor((int) event.getX(), (int) event.getY());
                    int redValue = Color.red(pixelColor);
                    int greenValue = Color.green(pixelColor);
                    int blueValue = Color.blue(pixelColor);
                    String tappedSector = getTappedSector(redValue, greenValue, blueValue);
                    if (!tappedSector.equals("")) {
                        findSector(tappedSector);
                        hideMap(v);
                        InflateFragment();
                    }

                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mTap = false;
                }
                return false;
            }
        });
    }

    private void findSector(String tappedSector) {
        for (Sector sector : mCarnival.getCarnivalSectors()) {
            if (sector.getSectorKey().equals(tappedSector)) {
                mCurrentSector = sector;
            }
        }
    }

    private String getTappedSector(int redValue, int greenValue, int blueValue) {
        String sectorKey = "";
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
        } else sectorKey = "";

        return sectorKey;
    }

    private boolean isInRange(int color, int trueValue) {
        if (color >= trueValue - 5 && color <= trueValue + 5)
            return true;
        return false;
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
        SectorFragment sectorFragment = new SectorFragment();
        sectorFragment.setCurrentSector(mCurrentSector);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, sectorFragment).commit();
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

        JSONObject jsonObject = new JSONObject(json.toString());

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

    private int getColor(int x, int y) {
        mImageMap.setDrawingCacheEnabled(true);
        Bitmap hotspots = Bitmap.createBitmap(mImageMap.getDrawingCache());
        mImageMap.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
    }
}
