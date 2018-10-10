package com.example.mozzie.mozlearning;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 16/12/27.
 */

public class MyApplication extends Application {

    private static Context mContext;

    @Override public void onCreate() {
        super.onCreate();
        mContext = this.getApplicationContext();

        initWechatSoter();
    }

    private void initWechatSoter() {

    }

    public static Context getMyContext(){
        return mContext;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
