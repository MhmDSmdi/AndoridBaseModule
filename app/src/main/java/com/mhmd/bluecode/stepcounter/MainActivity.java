package com.mhmd.bluecode.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView txtSensorList;
    private SensorManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        listLogger(deviceSensors, txtSensorList);
    }

    public void listLogger(List<Sensor> list, TextView textView) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Sensor sensor : list) {
            stringBuilder.append(sensor.toString());
            stringBuilder.append("\n -------------------------- \n");
        }
        textView.setText(stringBuilder.toString());
    }
}
