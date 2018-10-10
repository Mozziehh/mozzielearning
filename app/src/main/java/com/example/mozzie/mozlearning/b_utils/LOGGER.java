package com.example.mozzie.mozlearning.b_utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.ViewAnimationUtils;

/**
 * Created by mozzie on 16/12/5.
 */

public class LOGGER {
    public static String TAG = "MOZ_LOG_";

    public static void d(String key, String value){
        Log.d(TAG + key, value);
    }

    public static void d(Activity activity, String value){
        Log.d(TAG + activity.getLocalClassName(), value);
    }
}
