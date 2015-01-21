package com.alasitaappdroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alasitaappdroid.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class HistoryActivity extends ActionBarActivity {
    private ArrayList<String> mHistory;
    private ListView mListText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFBF01")));
        try {
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mListText = (ListView) findViewById(R.id.list_history);
        mListText.setFooterDividersEnabled(false);
        mListText.setHeaderDividersEnabled(false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mHistory);
        mListText.setAdapter(adapter);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }


    public String loadJSON(String s) {
        String json;
        try {
            InputStream inputStream = getAssets().open(s);
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


    public void loadEvents() throws JSONException {
        mHistory = new ArrayList<>();
        String json = loadJSON("history.json");
        JSONObject jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
        JSONArray events = jsonObject.getJSONArray("Historia");

        for (int i = 0; i < events.length(); i++) {
            JSONObject object = events.getJSONObject(i);

            String historia = object.getString("Texto");
            mHistory.add(historia);
        }
    }
}
