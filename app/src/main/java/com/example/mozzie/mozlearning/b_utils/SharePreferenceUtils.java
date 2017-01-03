package com.example.mozzie.mozlearning.b_utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mozzie.mozlearning.i_sharepreference.SharePreferenceActivity;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by mozzie on 17/1/3.
 */

public class SharePreferenceUtils {


    public static void saveString(){

    }

    public static void saveBoolean(){

    }

    //这个地方可以写的更丰富些，都能可配
    public static void save(Context context, String text) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("sharepreference", text);
        editor.apply();
    }

    //这个地方可以写的更丰富些，都能可配
    public static String get(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("data", MODE_PRIVATE);
        return sharedPreferences.getString("sharepreference","");
    }
}
