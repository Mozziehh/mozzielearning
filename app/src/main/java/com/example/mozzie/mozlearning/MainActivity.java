package com.example.mozzie.mozlearning;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.mozzie.mozlearning.a_activity.FoucesActivity;
import com.example.mozzie.mozlearning.a_activity.LaunchModeActivity;
import com.example.mozzie.mozlearning.a_activity.LifeCycleActivity;
import com.example.mozzie.mozlearning.c_database.DataBaseActivity;
import com.example.mozzie.mozlearning.d_intent.Aactivity;
import com.example.mozzie.mozlearning.f_fragment.FragmentActivity;
import com.example.mozzie.mozlearning.g_broadcastreceiver.BroadcastRecevierActivity;
import com.example.mozzie.mozlearning.h_contentprovider.ContentProviderConnectActivity;
import com.example.mozzie.mozlearning.i_sharepreference.SharePreferenceActivity;
import com.example.mozzie.mozlearning.j_notification.NotificationActivity;
import com.example.mozzie.mozlearning.k_service.MyServiceActivity;
import com.example.mozzie.mozlearning.l_http.HttpUrlConnectionAcitivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnLifeCycle(View view){
        Intent intent = new Intent();
        intent.setClass(this, LifeCycleActivity.class);
        startActivity(intent);
    }

    public void OnDatabase(View view){
        Intent intent = new Intent();
        intent.setClass(this, DataBaseActivity.class);
        startActivity(intent);
    }

    public void OnFocuse(View view){
        Intent intent = new Intent();
        intent.setClass(this, FoucesActivity.class);
        startActivity(intent);
    }

    public void OnIntent(View view){
        Intent intent = new Intent();
        intent.setClass(this, Aactivity.class);
        startActivity(intent);
    }

    /**
     * 优雅的启动activity
     * @param view
     */
    public void OnLaunchMode(View view){
        LaunchModeActivity.startActivity(this);
    }

    /**
     * 优雅的启动activity
     * @param view
     */
    public void OnFragmentLaunch(View view){
        FragmentActivity.startActivity(this);
    }

    /**
     * 优雅的启动activity
     * @param view
     */
    public void OnBroadcastLaunch(View view){
        BroadcastRecevierActivity.startActivity(this);
    }

    public void OnSharePreferenceLaunch(View view){
        SharePreferenceActivity.startActivity(this);
    }

    public void OnContentProviderLaunch(View view){
        ContentProviderConnectActivity.startActivity(this);
    }

    public void OnNotificationLaunch(View view){
        NotificationActivity.startActivity(this);
    }

    public void OnServiceLaunch(View view){
        MyServiceActivity.startActivity(this);
    }

    public void OnHttpLaunch(View view){
        HttpUrlConnectionAcitivity.startActivity(this);
    }

}
