package com.example.mozzie.mozlearning;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by mozzie on 16/12/27.
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }

    public static Context getMyContext(){
        return mContext;
    }
}
