package com.example.mozzie.mozlearning.z_work_drawerlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.z_work_drawerlayout.lib.SwipeBackLayout;
import com.example.mozzie.mozlearning.z_work_drawerlayout.lib.app.SwipeBackActivity;


/**
 * Created by mozzie on 17/2/24.
 */

public class MyTestSwipeActivity extends SwipeBackActivity{

    private SwipeBackLayout mSwipeBackLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MyTestSwipeActivity.class);
        context.startActivity(intent);
    }
}
