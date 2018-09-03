package com.mhmd.bluecode.stepcounter.timer;

public class Timer {

    private TimerType timerType;
    private TimeListener listener;
    private int hour, minute, second;
    private int timeSecond = 0;
    private Runnable timePicker;

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

    public void cansel() {
        if (timePicker != null) {
            try {
                timePicker.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void startTimer() {
        timeSecond = hour * 3600 + minute * 60 + second;
        listener.onStart();
        timePicker = new Runnable() {
            @Override
            public void run() {
                timeSecond--;
                if (timeSecond == 0) {
                    listener.onFinish();
                    cansel();
                }
                try {
                    listener.onTick();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(timePicker).start();
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

    enum TimerType {
        TIMER,
        STOP_WATCH
    }
}
