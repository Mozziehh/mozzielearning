package com.example.mozzie.mozlearning.j_notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mozzie.mozlearning.MainActivity;
import com.example.mozzie.mozlearning.R;

/**
 * Created by mozzie on 17/1/4.
 */

public class NotificationActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button button = (Button)findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new Notification(1,"",System.currentTimeMillis());
//                notification.
            }
        });
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, NotificationActivity.class);
        context.startActivity(intent);
    }
}
