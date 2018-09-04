package com.mhmd.bluecode.stepcounter.reminder;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.mhmd.bluecode.stepcounter.R;
import com.mhmd.bluecode.stepcounter.SensorActivity;

public class AlarmNotificationService extends IntentService {

    private NotificationManager notificationManager;

    public AlarmNotificationService() {
        super("AlarmNotificationService");
    }

    @Override
    public void onHandleIntent(Intent intent) {

    }

    //handle notification
    private void sendNotification(String msg) {
        notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        //get pending intent
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, SensorActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

    }

}
