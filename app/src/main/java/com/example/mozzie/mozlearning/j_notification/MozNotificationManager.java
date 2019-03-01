package com.example.mozzie.mozlearning.j_notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.widget.RemoteViews;

import com.example.mozzie.mozlearning.R;

public class MozNotificationManager {

    private final String mChannelID;
    private final String mChannelName;
    private final int mSmallIconId;
    private final Context mContext;

    private MozNotificationManager(NotificationManagerBuilder notificationManagerBuilder){
        mContext = notificationManagerBuilder.context;
        mChannelID = notificationManagerBuilder.channelID;
        mChannelName = notificationManagerBuilder.channelName;
        mSmallIconId = notificationManagerBuilder.smallIconID;
    }

    public static class NotificationManagerBuilder{
        private final Context context;

        private String channelID = "";
        private String channelName = "";
        private int smallIconID = -1;

        public NotificationManagerBuilder(Context context){
            this.context = context.getApplicationContext();
        }

        public NotificationManagerBuilder channelID(String channelID){
            this.channelID = channelID;
            return this;
        }

        public NotificationManagerBuilder channelName(String channelName){
            this.channelName = channelName;
            return this;
        }

        public NotificationManagerBuilder smallIcon(int smallIconID){
            this.smallIconID = smallIconID;
            return this;
        }

        public MozNotificationManager build(){
            return new MozNotificationManager(this);
        }
    }

    public void callNotification() {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel(mChannelID, mChannelName, NotificationManager.IMPORTANCE_DEFAULT);
                NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                manager.createNotificationChannel(channel);
                Notification.Builder builder = new Notification.Builder(mContext);
                RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout_withbutton2);
                builder.setCustomContentView(remoteViews);
                NotificationColorAdaptation.autoColorAdaptation(mContext)
                        .setContentTitleColor(remoteViews, R.id.notification_content_title)
                        .setContentTextColor(remoteViews, R.id.notification_content_text);
                remoteViews.setTextViewText(R.id.notification_content_title, "Custom ContentTitle O");
                remoteViews.setTextViewText(R.id.notification_content_text, "Custom ContentText O");
                remoteViews.setImageViewResource(R.id.notification_large_icon, mSmallIconId);

                builder.setTicker("Custom Ticker O")
                        .setCustomContentView(remoteViews)
                        .setSmallIcon(mSmallIconId)
                        .setAutoCancel(true)
                        .setChannelId(mChannelID);
                Notification notification = builder.build();

                manager.notify(1, notification);

            } else {
                Notification.Builder builder = new Notification.Builder(mContext);
                builder.setTicker("Custom Ticker")
                        .setSmallIcon(mSmallIconId);
                RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout_withbutton);

                builder.setContent(remoteViews);

                NotificationColorAdaptation.autoColorAdaptation(mContext)
                        .setContentTitleColor(remoteViews, R.id.notification_content_title)
                        .setContentTextColor(remoteViews, R.id.notification_content_text);
                remoteViews.setTextViewText(R.id.notification_content_title, "Custom ContentTitle");
                remoteViews.setTextViewText(R.id.notification_content_text, "Custom ContentText");
                remoteViews.setImageViewResource(R.id.notification_large_icon, mSmallIconId);
                ((NotificationManager)mContext.getSystemService(Context.NOTIFICATION_SERVICE)).notify(1, builder.getNotification());
            }
        }catch (Exception e){

        }
    }
}
