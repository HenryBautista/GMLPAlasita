package com.alasitaappdroid;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.alasitaappdroid.controller.adapter.EventAdapter;
import com.alasitaappdroid.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


public class ScheduleActivity extends ActionBarActivity {

    private ArrayList<Event> mActivityList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f58220")));
        try {
            loadEvents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        ListView scheduleListView = (ListView) findViewById(R.id.list_schedule);
        EventAdapter adapter = new EventAdapter(this, mActivityList);
        scheduleListView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    String loadJSON(String s) {
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


    void loadEvents() throws JSONException {
        String json = loadJSON("schedule.json");
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
        json = loadJSON("schedule2.json");
        jsonObject = new JSONObject(json.substring(json.indexOf("{"), json.lastIndexOf("}") + 1));
        events = jsonObject.getJSONArray("Exposiciones");
        EventList = new ArrayList<>();
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
