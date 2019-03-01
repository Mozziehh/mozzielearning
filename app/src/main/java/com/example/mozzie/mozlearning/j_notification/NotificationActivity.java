package com.example.mozzie.mozlearning.j_notification;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.R;
import com.example.mozzie.mozlearning.b_utils.LOGGER;

/**
 * Created by mozzie on 17/1/4.
 */

public class NotificationActivity extends Activity{

    private String channelID = "1";
    private String channelName = "channel_name";
    private final int mSmallIconId = R.mipmap.ic_launcher;

    MozNotificationManager mMozNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button button = (Button)findViewById(R.id.button8);

        mMozNotificationManager = new MozNotificationManager.NotificationManagerBuilder(this)
                .channelID("1")
                .channelName("channel_name")
                .smallIcon(mSmallIconId)
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mMozNotificationManager.callNotification();
                }catch (Exception e){
                    LOGGER.d("NotificationAcitivty", "error : " + e);
                }
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NotificationActivity.class);
        context.startActivity(intent);
    }
}
