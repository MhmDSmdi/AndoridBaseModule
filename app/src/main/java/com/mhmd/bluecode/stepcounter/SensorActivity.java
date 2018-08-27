package com.mhmd.bluecode.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView txtSensorList;
    private Button btnGetSensorList;
    private SensorManager mSensorManager;
    private Sensor mLight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        txtSensorList.setMovementMethod(new ScrollingMovementMethod());
        btnGetSensorList = findViewById(R.id.btn_sensorList);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        btnGetSensorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
                listLogger(deviceSensors, txtSensorList);
            }
        });
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

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
    public void onSensorChanged(SensorEvent sensorEvent) {
        float lux = sensorEvent.values[0];
        txtSensorList.setText(lux + " \n");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
