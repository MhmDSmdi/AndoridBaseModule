package com.mhmd.bluecode.stepcounter.timer;

import android.os.Handler;


public class Timer {

    private TimerType timerType;
    private TimeListener listener;
    private int hour, minute, second;
    private int timePerSecond = 0;
    private Runnable timePicker;
    private Handler timeHandler = new Handler();

    public Timer(TimerType type, TimeListener listener) {
        this.timerType = type;
        this.listener = listener;
    }

    public void start() {
        switch (timerType) {
            case TIMER:
                startTimer();
                break;
            case STOP_WATCH:
                startStopWatch();
                break;
        }
    }

    public void cancel() {
        timeHandler.removeCallbacks(timePicker);
    }

    private void startTimer() {
         timePerSecond = hour * 3600 + minute * 60 + second;
        listener.onStartTick();
        timePicker = new Runnable() {
            @Override
            public void run() {
                timeHandler.postDelayed(this, 1000);
                listener.onTick( timePerSecond);
                timePerSecond--;
                if (timePerSecond == 0) {
                    listener.onFinish();
                    cancel();
                }
            }
        };
        timeHandler.postAtTime(timePicker, 0);
    }

    private void startStopWatch() {
        timePicker = new Runnable() {
            @Override
            public void run() {

            }
        };
        new Thread(timePicker).start();
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public enum TimerType {
        TIMER,
        STOP_WATCH
    }
}
