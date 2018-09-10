package com.mhmd.bluecode.stepcounter;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mhmd.bluecode.stepcounter.notificationManger.BaseNotificationManager;
import com.mhmd.bluecode.stepcounter.notificationManger.NotificationListener;
import com.mhmd.bluecode.stepcounter.reminder.AlarmReceiver;

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
        notificationManager = new BaseNotificationManager(this);

        btnGetSensorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationManager.stopAlarmManager(pendingIntent);
            }
        });

        Intent alarmIntent = new Intent(SensorActivity.this, AlarmReceiver.class);
        alarmIntent.putExtra(NOTIFICATION_ID, 234);
        pendingIntent = PendingIntent.getBroadcast(SensorActivity.this, ALARM_REQUEST_CODE, alarmIntent, 0);
        notificationManager.triggerAlarmManager(2, pendingIntent);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public void onSendNotification() {

    }
}
