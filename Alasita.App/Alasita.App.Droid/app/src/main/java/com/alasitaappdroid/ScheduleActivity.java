package com.alasitaappdroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.alasitaappdroid.controller.adapter.EventAdapter;
import com.alasitaappdroid.model.Association;
import com.alasitaappdroid.model.Carnival;
import com.alasitaappdroid.model.Event;
import com.alasitaappdroid.model.Product;
import com.alasitaappdroid.model.Sector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class ScheduleActivity extends ActionBarActivity {

    private ListView mScheduleListView;
    private ArrayList<Event> mActivityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFBF01")));

        try {
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mScheduleListView = (ListView) findViewById(R.id.list_schedule);
        EventAdapter adapter = new EventAdapter(this, R.layout.adapter_schedule, mActivityList);
        mScheduleListView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public String loadJSON() {
        String json;
        try {
            InputStream inputStream = getAssets().open("schedule.json");
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
        String json = loadJSON();
        mActivityList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
        JSONArray events = jsonObject.getJSONArray("Actividades");
        ArrayList<Event> EventList = new ArrayList<>();
        for (int i = 0; i < events.length(); i++) {
            Event event = new Event();
            JSONObject jevent = events.getJSONObject(i);
            String date = jevent.getString("Fecha");
            String activity = jevent.getString("Actividad");
            String place = jevent.getString("Lugar");
            String time = jevent.getString("Hora");

            event.setEventActivity(activity);
            event.setEventDate(date);
            event.setEventPlace(place);
            event.setEventTime(time);

            mActivityList.add(event);
        }

    }

}
