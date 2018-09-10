package com.mhmd.bluecode.stepcounter.notificationManger;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.mhmd.bluecode.stepcounter.R;
import com.mhmd.bluecode.stepcounter.SensorActivity;
import com.mhmd.bluecode.stepcounter.reminder.AlarmNotificationService;
import com.mhmd.bluecode.stepcounter.reminder.AlarmSoundService;

import java.util.ArrayList;
import java.util.Calendar;
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

    public void triggerAlarmManager(int alarmTriggerTime, PendingIntent pendingIntent) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, alarmTriggerTime);
        AlarmManager manager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(mContext, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();
    }

    public void stopAlarmManager(PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        mContext.stopService(new Intent(mContext, AlarmSoundService.class));
        Toast.makeText(mContext, "Alarm Canceled/Stop by User.", Toast.LENGTH_SHORT).show();
    }
}
