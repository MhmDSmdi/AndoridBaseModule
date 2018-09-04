package com.mhmd.bluecode.stepcounter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mhmd.bluecode.stepcounter.notificationManger.BaseNotificationManager;
import com.mhmd.bluecode.stepcounter.notificationManger.NotificationListener;
import com.mhmd.bluecode.stepcounter.reminder.AlarmReceiver;
import com.mhmd.bluecode.stepcounter.reminder.AlarmSoundService;

import java.util.Calendar;

public class SensorActivity extends AppCompatActivity implements NotificationListener {

    private static final String TAG = "tesst";
    private static final String channelId = "notif.channel1";
    public static final String NOTIFICATION_ID = "NOTIFICATION_ID";
    private static final int ALARM_REQUEST_CODE = 133;
    private TextView txtSensorList;
    private Button btnGetSensorList;
    private BaseNotificationManager notificationManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        txtSensorList.setMovementMethod(new ScrollingMovementMethod());
        btnGetSensorList = findViewById(R.id.btn_sensorList);
        btnGetSensorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAlarmManager();
            }
        });

        Intent alarmIntent = new Intent(SensorActivity.this, AlarmReceiver.class);
        alarmIntent.putExtra(NOTIFICATION_ID, 234);
        pendingIntent = PendingIntent.getBroadcast(SensorActivity.this, ALARM_REQUEST_CODE, alarmIntent, 0);

        triggerAlarmManager(2);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void triggerAlarmManager(int alarmTriggerTime) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, alarmTriggerTime);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(this, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();
    }

    public void stopAlarmManager() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);//cancel the alarm manager of the pending intent
        stopService(new Intent(SensorActivity.this, AlarmSoundService.class));
//        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.cancel(AlarmNotificationService.);
        Toast.makeText(this, "Alarm Canceled/Stop by User.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSendNotification() {

    }
}
