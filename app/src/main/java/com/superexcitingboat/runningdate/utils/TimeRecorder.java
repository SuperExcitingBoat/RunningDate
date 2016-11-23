package com.superexcitingboat.runningdate.utils;

import java.util.ArrayList;

public class TimeRecorder {
    private boolean recording;
    private boolean enabled;
    private int count;
    private ArrayList<OnTimePlusOneSecondListener> listeners;
    private final TimeThread timeThread;
    private static TimeRecorder INSTANCE = new TimeRecorder();

    private TimeRecorder() {
        listeners = new ArrayList<>();
        count = 0;
        enabled = true;
        timeThread = new TimeThread();
    }

    public static TimeRecorder getInstance() {
        return INSTANCE;
    }

    public void switchStatus() {
        if (recording) {
            recording = false;
            timeThread.interrupt();
        } else {
            if (!timeThread.isAlive()) {
                timeThread.start();
            }
            synchronized (timeThread) {
                recording = true;
                timeThread.notify();
            }
        }
    }

    public interface OnTimePlusOneSecondListener {
        void onTimePlusOneSecond(int count);
    }

    public void removeOnTimePlusOneSecondListener(OnTimePlusOneSecondListener listener) {
        listeners.remove(listener);
    }

    public void addOnTimePlusOneSecondListener(OnTimePlusOneSecondListener listener) {
        listeners.add(listener);
    }

    public void disable() {
        enabled = false;
    }

    private class TimeThread extends Thread {
        @Override
        public void run() {
            while (enabled) {
                while (recording) {
                    try {
                        sleep(1000);
                        count++;
                        for (OnTimePlusOneSecondListener listener : listeners) {
                            listener.onTimePlusOneSecond(count);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                synchronized (timeThread) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
