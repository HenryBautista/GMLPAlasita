package com.alasitaappdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.alasitaappdroid.controller.fragment.SectorFragment;
import com.alasitaappdroid.model.Association;


public class AssociationActivity extends ActionBarActivity {

    private Association mCurrentAssociation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_association);
        Intent intent = getIntent();
        if (intent.getExtras().containsKey(SectorFragment.ASSOCIATION_NAME)) {
            //dummy content
            Toast.makeText(getApplicationContext(), intent.getExtras().getString(SectorFragment.ASSOCIATION_NAME), Toast.LENGTH_SHORT).show();
            //done
        }
        getSupportActionBar().setTitle("dummy 1");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_association, menu);
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
}
