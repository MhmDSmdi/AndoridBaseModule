package com.mhmd.bluecode.stepcounter.notificationManger;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.mhmd.bluecode.stepcounter.R;
import com.mhmd.bluecode.stepcounter.SensorActivity;

public class AlarmNotificationService extends IntentService {

    private static final int NOTIFICATION_ID = 1;
    private AlarmNotificationListener listener;


    public AlarmNotificationService(AlarmNotificationListener listener) {
        super("AlarmNotificationService");
        this.listener = listener;
    }

    @Override
    public void onHandleIntent(Intent intent) {
        listener.sendNotificationOnIntentCalled();
    }

   /* //handle notification
    private void sendNotification(String msg) {
        NotificationManager alarmNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, SensorActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        //Create notification

        NotificationCompat.Builder alarmNotificationBuilder = new NotificationCompat.Builder(
                this).setContentTitle("Alarm").setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setContentText(msg).setAutoCancel(true);
        alarmNotificationBuilder.setContentIntent(contentIntent);

        //notify notification manager about new notification
        alarmNotificationManager.notify(NOTIFICATION_ID, alarmNotificationBuilder.build());
    }*/


}
