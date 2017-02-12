package com.example.mozzie.mozlearning.k_service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 17/2/12.
 */

public class MyServiceActivity extends Activity implements View.OnClickListener{

    private Button mStartService,mStopService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mStartService = (Button) findViewById(R.id.button9);
        mStopService = (Button) findViewById(R.id.button10);

        mStartService.setOnClickListener(this);
        mStopService.setOnClickListener(this);
    }

    /**
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button9:
                Intent startIntent = new Intent(MyServiceActivity.this, MyService.class);
                startService(startIntent);
                break;
            case R.id.button10:
                Intent stopIntent = new Intent(MyServiceActivity.this, MyService.class);
                stopService(stopIntent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
