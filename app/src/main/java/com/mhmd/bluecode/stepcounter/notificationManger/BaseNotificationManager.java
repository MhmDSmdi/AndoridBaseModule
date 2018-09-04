package com.mhmd.bluecode.stepcounter.notificationManger;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.mhmd.bluecode.stepcounter.R;

import java.util.ArrayList;
import java.util.List;


public class BaseNotificationManager {

    private NotificationManagerCompat compatNotificationManager;
    private NotificationManager notificationManager;
    private NotificationListener listener;
    private List<Notification> notificationList;
    private Context mContext;

    public BaseNotificationManager(Context mContext) {
        this.mContext = mContext;
        notificationList = new ArrayList<>();
        compatNotificationManager = NotificationManagerCompat.from(mContext);
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

    }

    public BaseNotificationManager(Context mContext, NotificationListener listener) {
        this.mContext = mContext;
        this.listener = listener;
        notificationList = new ArrayList<>();
        compatNotificationManager = NotificationManagerCompat.from(mContext);
        notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public void createNotificationChannel(int channelNameResource, int channelDescriptionResource, String channelID ,int importance) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelID, mContext.getString(channelNameResource), importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(R.color.colorAccent);
            notificationChannel.canShowBadge();
            notificationChannel.setDescription(mContext.getString(channelDescriptionResource));
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    public void notifyNotification(Notification notification, int id) {
        if (listener != null)
            listener.onSendNotification();
        notificationList.add(notification);
        compatNotificationManager.notify(id, notification);
    }
}
