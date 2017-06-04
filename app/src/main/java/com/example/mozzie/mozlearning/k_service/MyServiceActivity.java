package com.example.mozzie.mozlearning.k_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/2/12.
 */

public class MyServiceActivity extends Activity implements View.OnClickListener{

    private Button mStartService,mStopService,mBind,mUnbind,mIntentService;
    private MyService.DownloadBinder mDownloadBinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mStartService = (Button) findViewById(R.id.button9);
        mStopService = (Button) findViewById(R.id.button10);
        mBind = (Button) findViewById(R.id.button11);
        mUnbind = (Button) findViewById(R.id.button12);
        mIntentService = (Button) findViewById(R.id.button13);

        mStartService.setOnClickListener(this);
        mStopService.setOnClickListener(this);
        mBind.setOnClickListener(this);
        mUnbind.setOnClickListener(this);
        mIntentService.setOnClickListener(this);
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
            case R.id.button11:
                Intent bindIntent = new Intent(this, MyService.class);
                bindService(bindIntent, mServiceConnection, BIND_AUTO_CREATE); // 绑定服务
                break;
            case R.id.button12:
                unbindService(mServiceConnection); // 解绑服务
                break;
            case R.id.button13:
                LOGGER.d("MyIntentService", "Main Thread id is " + Thread.currentThread(). getId());
                Intent startIntentService = new Intent(MyServiceActivity.this, MyIntentService.class);
                startService(startIntentService);
                break;
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (MyService.DownloadBinder) service;
            mDownloadBinder.startDownload();
            mDownloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyServiceActivity.class);
        context.startActivity(intent);
    }
}
