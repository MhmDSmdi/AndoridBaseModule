package com.mhmd.bluecode.stepcounter.stepCounter;

public interface StepCounterListener {

    void onUpdateStepCounter(int steps);

    void onUpdateStepDetector(int stepDetector);
}
