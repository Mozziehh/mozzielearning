package com.example.mozzie.mozlearning.z_work_drawerlayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.example.mozzie.mozlearning.R;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.ViewDragHelper;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by mozzie on 17/2/22.
 */

public class SimpleSwipeActivity extends BaseActivity{

    private int[] mBgColors;

    private static int mBgIndex = 0;

    private String mKeyTrackingMode;

    private RadioGroup mTrackingModeGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpleswipe_layout);
    }
//
//    /**
//     * Called when a view has been clicked.
//     *
//     * @param v The view that was clicked.
//     */
//    @Override
//    public void onClick(View v) {
//
//    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SimpleSwipeActivity.class);
        context.startActivity(intent);
    }
}
