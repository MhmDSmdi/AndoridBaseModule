package com.mhmd.bluecode.stepcounter;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mhmd.bluecode.stepcounter.reminder.AlarmNotificationService;
import com.mhmd.bluecode.stepcounter.reminder.AlarmReceiver;
import com.mhmd.bluecode.stepcounter.reminder.AlarmSoundService;

import java.util.Calendar;
import java.util.List;

public class SensorActivity extends AppCompatActivity {

    private static final String TAG = "tesst";
    private static final int ALARM_REQUEST_CODE = 133;
    private TextView txtSensorList;
    private Button btnGetSensorList;
    private PendingIntent pendingIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        txtSensorList.setMovementMethod(new ScrollingMovementMethod());
        btnGetSensorList = findViewById(R.id.btn_sensorList);
        Intent alarmIntent = new Intent(SensorActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(SensorActivity.this, ALARM_REQUEST_CODE, alarmIntent, 0);
        triggerAlarmManager(1);
        btnGetSensorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAlarmManager();
            }
        });
        /*LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                location.getLatitude();
                Toast.makeText(SensorActivity.this, "Current speed:" + (location.getSpeed()*3600.0)/1000.0, Toast.LENGTH_SHORT).show();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(SensorActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(SensorActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SensorActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            return;
        }else{
            // Write you code here if permission already given.
        }


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, locationListener);*/



    }

    public void listLogger(List<Sensor> list, TextView textView) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Sensor sensor : list) {
            stringBuilder.append(sensor.toString());
            stringBuilder.append("\n -------------------------- \n");
        }
        textView.setText(stringBuilder.toString());
    }

    //Trigger alarm manager with entered time interval
    public void triggerAlarmManager(int alarmTriggerTime) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, alarmTriggerTime);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);//get instance of alarm manager
        manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);//set alarm manager with entered timer by converting into milliseconds
        Toast.makeText(this, "Alarm Set for " + alarmTriggerTime + " seconds.", Toast.LENGTH_SHORT).show();
    }

    public void stopAlarmManager() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);//cancel the alarm manager of the pending intent
        stopService(new Intent(SensorActivity.this, AlarmSoundService.class));
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(AlarmNotificationService.NOTIFICATION_ID);
        Toast.makeText(this, "Alarm Canceled/Stop by User.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
