package com.mhmd.bluecode.stepcounter.stepCounter;

import android.hardware.SensorEventListener;

public interface StepCounterSensorEventListener extends SensorEventListener {

    void onRegisterSensor();

    void onUnRegisterSensor();
}
