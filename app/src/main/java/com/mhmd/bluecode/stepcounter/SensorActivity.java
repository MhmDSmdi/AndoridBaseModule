package com.mhmd.bluecode.stepcounter;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.mhmd.bluecode.stepcounter.stepCounter.StepCounterListener;
import com.mhmd.bluecode.stepcounter.stepCounter.StepCounterSensor;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements StepCounterListener {

    private static final String TAG = "tag";
    private TextView txtSensorList;
    private Button btnGetSensorList;
    private StepCounterSensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtSensorList = findViewById(R.id.txt_availableSensor);
        txtSensorList.setMovementMethod(new ScrollingMovementMethod());
        btnGetSensorList = findViewById(R.id.btn_sensorList);
        sensor = new StepCounterSensor(this, this);

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
        sensor.onRegisterSensor();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensor.onUnRegisterSensor();
    }

    @Override
    public void onUpdateStepCounter(int steps) {
        txtSensorList.setText(steps + "");
    }

    @Override
    public void onUpdateStepDetector(int stepDetector) {
    }
}
