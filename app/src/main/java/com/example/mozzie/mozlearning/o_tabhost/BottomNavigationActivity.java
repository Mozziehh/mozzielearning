package com.example.mozzie.mozlearning.o_tabhost;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

/**
 * Created by mozzie on 17/6/1.
 */

public class BottomNavigationActivity extends FragmentActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private BottomNavigationView mBottomNavigationView;
    private FrameLayout mCategoryPage;
    private Fragment mCurrentFragment;
    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private FragmentD fragmentD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        initFragment();
    }

    private void initFragment() {
        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigationview);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mCategoryPage = (FrameLayout) findViewById(R.id.category_page);
        mCurrentFragment = new FragmentA();
//        mBottomNavigationView = (BottomNavigationView) findViewById(R.id.)
    }

    /**
     * Called when an item in the bottom navigation menu is selected.
     *
     * @param item The selected item
     * @return true to display the item as the selected item and false if the item should not
     * be selected. Consider setting non-selectable items as disabled preemptively to
     * make them appear non-interactive.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_nav_ui:
                if(fragmentA == null){
                    fragmentA = new FragmentA();
                }
                changeFragment(fragmentA);
                ToastUtils.show(BottomNavigationActivity.this, "1");
                break;
            case R.id.bottom_nav_data:
                changeFragment(fragmentA);
                ToastUtils.show(BottomNavigationActivity.this, "2");
                break;
            case R.id.bottom_nav_service:
                changeFragment(fragmentA);
                ToastUtils.show(BottomNavigationActivity.this, "3");
                break;
            case R.id.bottom_nav_net:
                changeFragment(fragmentA);
                ToastUtils.show(BottomNavigationActivity.this, "4");
                break;
        }
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, BottomNavigationActivity.class);
        context.startActivity(intent);
    }

    private void changeFragment(Fragment toFragment) {
        if (mCurrentFragment != toFragment) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            if (toFragment.isAdded() == false) {
                ft.hide(mCurrentFragment).add(R.id.category_page, toFragment).commit();
            } else {
                ft.hide(mCurrentFragment).show(toFragment).commit();
            }
        }
    }

    private void changeFragment(Fragment fromFragment, Fragment toFragment) {

        if (mCurrentFragment != toFragment) {
            mCurrentFragment = toFragment;
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (toFragment.isAdded() == false) {
            ft.hide(fromFragment).add(R.id.category_page, toFragment).commit();
        } else {
            ft.hide(fromFragment).show(toFragment).commit();
        }

    }
}
