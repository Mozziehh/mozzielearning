package com.example.mozzie.mozlearning.a_activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 16/12/5.
 */

public class LifeCycleActivity extends Activity{

    @Override
    protected void onStart() {
        super.onStart();
        LOGGER.d(this, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LOGGER.d(this, "onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        LOGGER.d(this, "onCreate");
        ImageView imageView = (ImageView) findViewById(R.id.lifecycle);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.life_cycle));
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGGER.d(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LOGGER.d(this, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LOGGER.d(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LOGGER.d(this, "onDestroy");
    }

}
