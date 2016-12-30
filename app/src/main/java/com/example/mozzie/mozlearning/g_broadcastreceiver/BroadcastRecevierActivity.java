package com.example.mozzie.mozlearning.g_broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;
import com.example.mozzie.mozlearning.d_intent.Aactivity;

/**
 * Created by mozzie on 16/12/30.
 */

public class BroadcastRecevierActivity extends Activity{

    private IntentFilter mBroadIntentFilter;
    private MyBroadcastReceiver myBroadcastReceiver;
    private LocalBroadcastManager mLocalBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastreceiver);

        mBroadIntentFilter = new IntentFilter();
        mBroadIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        myBroadcastReceiver = new MyBroadcastReceiver();
        registerReceiver(myBroadcastReceiver, mBroadIntentFilter);

        mLocalBroadcast = LocalBroadcastManager.getInstance(BroadcastRecevierActivity.this);

        Button button = (Button) findViewById(R.id.broadcast_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.SELFDEF_BROADRECEIVER");
                sendBroadcast(intent);
//                mLocalBroadcast.sendBroadcast(intent);
            }
        });

//        mBroadIntentFilter = new IntentFilter();
//        mBroadIntentFilter.addAction("android.intent.action.SELFDEF_BROADRECEIVER");
//        myBroadcastReceiver = new MyBroadcastReceiver();
//        mLocalBroadcast.registerReceiver(myBroadcastReceiver,mBroadIntentFilter);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, BroadcastRecevierActivity.class);
        context.startActivity(intent);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        /**
         * This method is called when the BroadcastReceiver is receiving an Intent
         * broadcast.  During this time you can use the other methods on
         * BroadcastReceiver to view/modify the current result values.  This method
         * is always called within the main thread of its process, unless you
         * explicitly asked for it to be scheduled on a different thread using
         * {@link Context#registerReceiver(BroadcastReceiver,
         * IntentFilter, String, Handler)}. When it runs on the main
         * thread you should
         * never perform long-running operations in it (there is a timeout of
         * 10 seconds that the system allows before considering the receiver to
         * be blocked and a candidate to be killed). You cannot launch a popup dialog
         * in your implementation of onReceive().
         * <p>
         * <p><b>If this BroadcastReceiver was launched through a &lt;receiver&gt; tag,
         * then the object is no longer alive after returning from this
         * function.</b>  This means you should not perform any operations that
         * return a result to you asynchronously -- in particular, for interacting
         * with services, you should use
         * {@link Context#startService(Intent)} instead of
         * {@link Context#/bindService(Intent, ServiceConnection, int)}.  If you wish
         * to interact with a service that is already running, you can use
         * {@link #peekService}.
         * <p>
         * <p>The Intent filters used in {@link Context#registerReceiver}
         * and in application manifests are <em>not</em> guaranteed to be exclusive. They
         * are hints to the operating system about how to find suitable recipients. It is
         * possible for senders to force delivery to specific recipients, bypassing filter
         * resolution.  For this reason, {@link #onReceive(Context, Intent) onReceive()}
         * implementations should respond only to known actions, ignoring any unexpected
         * Intents that they may receive.
         *
         * @param context The Context in which the receiver is running.
         * @param intent  The Intent being received.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            ToastUtils.show(context, "network changes");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
//        mLocalBroadcast.unregisterReceiver(myBroadcastReceiver);
    }
}
