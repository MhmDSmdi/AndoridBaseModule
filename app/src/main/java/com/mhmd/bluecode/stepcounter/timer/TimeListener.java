package com.mhmd.bluecode.stepcounter.timer;

public interface TimeListener {

    void onTick(int timePerSecond);

    void onFinish();

    void onStartTick();
}
