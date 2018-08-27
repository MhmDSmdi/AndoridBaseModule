package com.mhmd.bluecode.stepcounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "tag";
    private TextView txtSensorList;
    private Button btnGetSensorList;
    private SensorManager mSensorManager;
    private Sensor mStep;

    private int stepDetector = 0;
    private int counterSteps = 0;
    private int stepCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        txtSensorList.setMovementMethod(new ScrollingMovementMethod());
        btnGetSensorList = findViewById(R.id.btn_sensorList);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mStep = mSensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        btnGetSensorList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
                listLogger(deviceSensors, txtSensorList);
            }
        });

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
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                if (counterSteps < 1) {
                    counterSteps = (int)sensorEvent.values[0];
                }
                stepCounter = (int)sensorEvent.values[0] - counterSteps;
                txtSensorList.setText(stepCounter + "");
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                stepDetector++;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mStep, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}
