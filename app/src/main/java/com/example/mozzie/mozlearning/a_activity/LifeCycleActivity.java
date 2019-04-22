package com.example.mozzie.mozlearning.a_activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

/**
 * Created by mozzie on 16/12/5.
 */

public class LifeCycleActivity extends Activity{

    private static String TAG = "huhao-LifeCycleActivity";

    @Override
    protected void onStart() {
        super.onStart();
        LOGGER.d(TAG, "onStart");
        String test = "";
        if(!TextUtils.isEmpty(test)){
            test.equals("on");
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LOGGER.d(TAG, "onRestart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        LOGGER.d(TAG, "onCreate");
        ImageView imageView = (ImageView) findViewById(R.id.lifecycle);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.life_cycle));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchModeActivity.startActivity(LifeCycleActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LOGGER.d(TAG, "onResume");
        Integer i  =  Integer.valueOf(65535);
        Object o = i;
        ToastUtils.show(this, String.valueOf(o));

        Integer i_fushu = Integer.valueOf(-1);
        ToastUtils.show(this, String.valueOf(i_fushu));

        Object o_null = null;
        ToastUtils.show(this, String.valueOf(o_null));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LOGGER.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LOGGER.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LOGGER.d(TAG, "onDestroy");
    }

}
