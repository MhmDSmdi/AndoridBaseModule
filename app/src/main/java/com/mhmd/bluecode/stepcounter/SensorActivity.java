package com.mhmd.bluecode.stepcounter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mhmd.bluecode.stepcounter.stepCounter.StepCounterListener;
import com.mhmd.bluecode.stepcounter.stepCounter.StepCounterSensor;
import com.mhmd.bluecode.stepcounter.timer.TimeListener;
import com.mhmd.bluecode.stepcounter.timer.Timer;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements TimeListener {

    private static final String TAG = "tesst";
    private TextView txtSensorList;
    private Button btnGetSensorList;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        txtSensorList.setMovementMethod(new ScrollingMovementMethod());
        btnGetSensorList = findViewById(R.id.btn_sensorList);
        timer = new Timer(Timer.TimerType.TIMER, this);
        timer.setHour(0);
        timer.setMinute(0);
        timer.setSecond(10);
        timer.start();
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onTick(int timePerSecond) {
        Log.wtf(TAG, "onTick: " + timePerSecond);
    }

    @Override
    public void onFinish() {
        Log.wtf(TAG, "onFinish: ");
    }

    @Override
    public void onStartTick() {
        Log.wtf(TAG, "onStartTick: " );
    }
}
