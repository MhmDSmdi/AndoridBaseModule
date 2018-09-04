package com.mhmd.bluecode.stepcounter.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import com.mhmd.bluecode.stepcounter.R;
import com.mhmd.bluecode.stepcounter.SensorActivity;
import com.mhmd.bluecode.stepcounter.notificationManger.BaseNotificationManager;

public class AlarmNotificationService extends IntentService {

    private BaseNotificationManager notificationManager;

    public AlarmNotificationService() {
        super("AlarmNotificationService");
    }

    @Override
    public void onHandleIntent(Intent intent) {
        int type = intent.getIntExtra("NOTIFICATION_TYPE", -1);
        sendNotification(type);
    }

    //handle notification
    private void sendNotification(int notificationId) {
        notificationManager = new BaseNotificationManager(this);
        notificationManager.createNotificationChannel(R.string.channelName, R.string.channelDescription, getString(R.string.channelId), NotificationManager.IMPORTANCE_DEFAULT);
        Notification.Builder notificationBuilder = new Notification.Builder(this);
        notificationBuilder.setContentText(notificationId + " is Notification ID");
        notificationBuilder.setSmallIcon(R.drawable.ic_add_plus);
        notificationBuilder.setContentTitle("Test");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationBuilder.setChannelId(getString(R.string.channelId));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationManager.notifyNotification(notificationBuilder.build(), notificationId);
        }
        //get pending intent
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, SensorActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

    }

}
