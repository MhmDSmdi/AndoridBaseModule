package com.mhmd.bluecode.stepcounter.stepCounter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;

public class StepCounterSensorEvent implements StepCounterSensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorStepCounter;
    private Sensor sensorStepDetector;
    private Context mContext;
    private int stepsDetector = 0;
    private int stepInitializer = 0;
    private int stepCounter = 0;

    public StepCounterSensorEvent(Context mContext) {
        this.mContext = mContext;
        sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
        sensorStepCounter = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorStepDetector = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()) {
            case Sensor.TYPE_STEP_COUNTER:
                if (stepInitializer < 1) {
                    stepInitializer = (int)sensorEvent.values[0];
                }
                stepCounter = (int)sensorEvent.values[0] - stepInitializer;
                break;
            case Sensor.TYPE_STEP_DETECTOR:
                stepsDetector++;
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public void onRegisterSensor() {
        sensorManager.registerListener(this, sensorStepCounter, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
        sensorManager.registerListener(this, sensorStepDetector, SensorManager.SENSOR_STATUS_ACCURACY_HIGH);
    }

    @Override
    public void onUnRegisterSensor() {
        sensorManager.unregisterListener(this);
    }

}
