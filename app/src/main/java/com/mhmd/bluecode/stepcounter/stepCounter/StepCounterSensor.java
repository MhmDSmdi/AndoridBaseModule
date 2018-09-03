package com.mhmd.bluecode.stepcounter.stepCounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class StepCounterSensor implements SensorEventListener {

    private static final String TAG = "tag";
    private SensorManager sensorManager;
    private Sensor sensorStepCounter;
    private Sensor sensorStepDetector;
    private StepCounterListener stepCounterListener;
    private Context mContext;

    private int stepsDetector = 0;
    private int stepInitializer = 0;
    private int stepCounter = 0;

    public StepCounterSensor(Context mContext, StepCounterListener stepCounterListener) {
        this.mContext = mContext;
        this.stepCounterListener = stepCounterListener;
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        sensorStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.wtf(TAG, "onSensorChanged: " + "32");
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                if (stepInitializer < 1) {
                    stepInitializer = (int)sensorEvent.values[0];
                }
                stepCounter = (int)sensorEvent.values[0] - stepInitializer;
                stepCounterListener.onUpdateStepCounter(stepCounter);
                Log.wtf(TAG, "onSensorChanged: ");
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                stepsDetector++;
                stepCounterListener.onUpdateStepDetector(stepsDetector);
                Log.wtf(TAG, "onSensorChanged: ");
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void onRegisterSensor() {
        Log.wtf(TAG, "onRegisterSensor: ");
        sensorManager.registerListener(this, sensorStepCounter, SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, sensorStepDetector, SensorManager.SENSOR_DELAY_FASTEST);
    }

    public void onUnRegisterSensor() {
        Log.wtf(TAG, "onUnRegisterSensor: ");
        sensorManager.unregisterListener(this);
    }

}
