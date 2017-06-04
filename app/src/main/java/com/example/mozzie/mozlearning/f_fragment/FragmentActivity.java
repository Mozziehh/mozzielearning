package com.example.mozzie.mozlearning.f_fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 16/12/29.
 */

public class FragmentActivity extends android.support.v4.app.FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity_layout);
//        Fragment afragment = (AFragment) getFragmentManager().findFragmentById(R.id.left_fragment);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, FragmentActivity.class);
        context.startActivity(intent);
    }
}
