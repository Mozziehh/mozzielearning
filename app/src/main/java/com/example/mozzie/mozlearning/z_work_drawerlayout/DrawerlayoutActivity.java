package com.example.mozzie.mozlearning.z_work_drawerlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.d_intent.Aactivity;

/**
 * Created by mozzie on 17/2/22.
 */

public class DrawerlayoutActivity extends Activity {

    private DrawerLayout mDrawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawerlayout);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
//        mDrawerLayout.openDrawer(GravityCompat.START);
        final Button button15 = (Button)findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(button15);
            }
        });
        final Button button18 = (Button)findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleSwipeActivity.startActivity(DrawerlayoutActivity.this);
            }
        });
        final Button button19 = (Button)findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrawerlayoutActivity.this, MyTestSwipeActivity.class);
                startActivity(intent);
//                MyTestSwipeActivity.startActivity(DrawerlayoutActivity.this);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DrawerlayoutActivity.class);
        context.startActivity(intent);
    }
}
