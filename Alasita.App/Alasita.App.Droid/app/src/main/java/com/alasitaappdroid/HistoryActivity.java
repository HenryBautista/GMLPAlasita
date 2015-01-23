package com.alasitaappdroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class HistoryActivity extends ActionBarActivity {
    private ArrayList<String> mHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f58220")));
        try {
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ListView listText = (ListView) findViewById(R.id.list_history);
        listText.setFooterDividersEnabled(false);
        listText.setHeaderDividersEnabled(false);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mHistory);
        listText.setAdapter(adapter);
        ImageView imageView = (ImageView) findViewById(R.id.background);
        try {
            if (Build.VERSION.SDK_INT > 11) {
                imageView.setAlpha(0.3f);
            } else {
                imageView.setVisibility(View.INVISIBLE);
            }
        } catch (Exception e) {
            Log.d("Debug Error", e.getMessage());
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    String loadJSON() {
        String json;
        try {
            InputStream inputStream = getAssets().open("history.json");
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


    void loadEvents() throws JSONException {
        mHistory = new ArrayList<>();
        String json = loadJSON();
        JSONObject jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
        JSONArray events = jsonObject.getJSONArray("Historia");

        for (int i = 0; i < events.length(); i++) {
            JSONObject object = events.getJSONObject(i);

            String historia = object.getString("Texto");
            mHistory.add(historia);
        }
    }
}
