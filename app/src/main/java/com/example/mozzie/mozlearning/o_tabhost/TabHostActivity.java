package com.example.mozzie.mozlearning.o_tabhost;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.d_intent.Aactivity;

/**
 * Created by mozzie on 17/5/15.
 */

public class TabHostActivity extends FragmentActivity implements TabHost.OnTabChangeListener{

    private FragmentTabHost mFragmentTabHost;
    private LinearLayout mTabWidget;
    private String[] TabsName = {"11","22","33","44"};

    private int[] TabsIcon = {R.drawable.leak_canary_icon, R.drawable.leak_canary_icon, R.drawable.leak_canary_icon, R.drawable.leak_canary_icon};
    private Class[] TabsFragment = {FragmentA.class, FragmentB.class, FragmentC.class, FragmentD.class};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);

        initFragment();
    }

    private void initFragment() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabhost);
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
        mTabWidget = (LinearLayout) findViewById(R.id.mytab);
        for(int i = 0 ; i < TabsName.length; i ++){
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(TabsName[i]).setIndicator(TabsName[i]);
            mFragmentTabHost.addTab(tabSpec,TabsFragment[i],null);
            mFragmentTabHost.getTabWidget().getChildAt(i).setBackgroundResource(android.R.color.white);
        }
        mFragmentTabHost.setOnTabChangedListener(this);
    }

    public LinearLayout getTabHost(){
        return mTabWidget;
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
        Intent intent = new Intent(context, TabHostActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onTabChanged(String s) {
        LOGGER.d("TabHostActivity", s);
    }
}
