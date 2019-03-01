package com.example.mozzie.mozlearning.n_listview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mozzie.mozlearning.b_utils.LOGGER;

public class HackedListView extends ListView {
    final String TAG = HackedListView.class.getSimpleName();

    public HackedListView(Context context) {
        super(context);
    }

    public HackedListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HackedListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HackedListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void layoutChildren() {
        try {
            super.layoutChildren();
        } catch (Throwable e) {
            LOGGER.d(TAG, "catch ListView crash: " + getId());
            autoNotifyAdapter(true);
//            CatchUICrashManager.getInstance().sendToBugly(e);
        }
    }

    private void autoNotifyAdapter(boolean force) {
        ListAdapter adapter = getAdapter();
        if (adapter instanceof BaseAdapter) {
            boolean notify = force | adapter.getCount() != getCount();
            if (notify) {
                LOGGER.d(TAG, "auto notify:force=" + force);
                ((BaseAdapter) adapter).notifyDataSetChanged();
            }
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        autoNotifyAdapter(false);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
