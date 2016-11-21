package com.superexcitingboat.runningdate.utils.Counter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.ArrayList;

import static android.content.Context.SENSOR_SERVICE;

public class StepRecorder implements StepDetector.OnSensorChangeListener {
    private int count;
    private StepDetector stepDetector;
    private SensorManager sensorManager;
    private static StepRecorder ourInstance = new StepRecorder();
    ;
    private ArrayList<OnStepChangeListener> onStepChangeListeners;

    private StepRecorder() {
    }

    public static StepRecorder getInstance() {
        return ourInstance;
    }

    public interface OnStepChangeListener {
        void onStepChange(int step);
    }

    public void removeOnStepChangeListener(OnStepChangeListener onStepChangeListener) {
        onStepChangeListeners.remove(onStepChangeListener);
    }

    public void addOnStepChangeListener(OnStepChangeListener onStepChangeListener) {
        this.onStepChangeListeners.add(onStepChangeListener);
    }

    public int getCount() {
        return count;
    }

    public void init(Context context) {
        stepDetector = new StepDetector();
        sensorManager = (SensorManager) context.getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(stepDetector,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
        stepDetector.setOnSensorChangeListener(this);
        onStepChangeListeners = new ArrayList<>();
    }

    @Override
    public void onChange(int step) {
        count = step;
        for (OnStepChangeListener onStepChangeListener : onStepChangeListeners) {
            onStepChangeListener.onStepChange(count);
        }
    }
}
