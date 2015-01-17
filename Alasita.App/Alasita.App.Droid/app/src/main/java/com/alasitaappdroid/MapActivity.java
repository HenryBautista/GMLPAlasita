package com.alasitaappdroid;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.alasitaappdroid.controller.fragment.SectorFragment;


public class MapActivity extends ActionBarActivity {

    private ImageView mImageMap;
    private FrameLayout mFrameContainer;
    private boolean mTap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

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

                    // Implement getPixel()

                    hide(v);
                    InflateFragment();
                }
                if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    mTap = false;
                }
                return false;
            }
        });
    }


    private void hide(View v) {
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
        mFrameContainer.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().add(R.id.container, new SectorFragment()).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
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

    public void hideFragment() {
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
}
