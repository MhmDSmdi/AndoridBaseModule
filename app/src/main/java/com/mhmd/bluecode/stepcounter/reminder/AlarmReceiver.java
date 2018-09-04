package com.mhmd.bluecode.stepcounter.reminder;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //DO when Notification Called
        Toast.makeText(context, "OnReceive", Toast.LENGTH_LONG).show();
        context.startService(new Intent(context, AlarmSoundService.class));
        ComponentName comp = new ComponentName(context.getPackageName(), AlarmNotificationService.class.getName());
//        intent.putExtra("NOTIFICATION_TYPE", );
        startWakefulService(context, (intent.setComponent(comp)));
    }

}
