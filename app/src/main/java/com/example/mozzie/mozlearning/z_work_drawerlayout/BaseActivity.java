package com.example.mozzie.mozlearning.z_work_drawerlayout;

import android.os.Bundle;
import android.view.View;

import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by mozzie on 17/2/22.
 */

public class BaseActivity extends SwipeBackActivity{

    private SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeBackLayout = getSwipeBackLayout();
        //SwipeBackLayout.EDGE_RIGHT  |  SwipeBackLayout.EDGE_BOTTOM  | SwipeBackLayout.EDGE_ALL
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT | SwipeBackLayout.EDGE_RIGHT);
    }

//    /**
//     * Called when a view has been clicked.
//     *
//     * @param v The view that was clicked.
//     */
//    @Override
//    public void onClick(View v) {
//
//    }
}
