package com.mhmd.bluecode.stepcounter.notificationManger;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //DO when Notification Called
        context.startService(new Intent(context, AlarmSoundService.class));
        ComponentName comp = new ComponentName(context.getPackageName(), AlarmNotificationService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
    }

}
