package com.example.mozzie.mozlearning.g_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;


/**
 * Created by mozzie on 16/12/30.
 */

public class SelfDefineBroadcast extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        com.example.mozzie.mozlearning.b_utils.ToastUtils.show(context, "接收自定义广播");
        com.example.mozzie.mozlearning.b_utils.LOGGER.d("SelfDefineBroadcast", "接收自定义广播");
        //接收到就丢弃，不往下传递
        abortBroadcast();
    }
}
