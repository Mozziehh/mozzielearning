package com.example.mozzie.mozlearning.g_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.mozzie.mozlearning.b_utils.LOGGER;
import com.example.mozzie.mozlearning.b_utils.ToastUtils;

/**
 * Created by mozzie on 16/12/30.
 */

public class SelfDefineBroadcast extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        ToastUtils.show(context, "接收自定义广播");
        LOGGER.d("SelfDefineBroadcast", "接收自定义广播");
        //接收到就丢弃，不往下传递
        abortBroadcast();
    }
}
