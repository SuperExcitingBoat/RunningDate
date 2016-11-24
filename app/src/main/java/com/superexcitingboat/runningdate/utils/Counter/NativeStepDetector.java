package com.superexcitingboat.runningdate.utils.Counter;

import android.hardware.Sensor;
import android.hardware.SensorEvent;

public class NativeStepDetector implements TypedSensorEventListener {
    private int count;
    private OnStepChangeListener onStepChangeListener;

    public NativeStepDetector() {
        count = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            onStepChangeListener.onStepChange(++count);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    public int getSensorType() {
        return Sensor.TYPE_STEP_DETECTOR;
    }

    @Override
    public void setOnStepChangeListener(OnStepChangeListener onStepChangeListener) {
        this.onStepChangeListener = onStepChangeListener;
    }

    @Override
    public void removeOnStepChangeListener() {
        onStepChangeListener = null;
    }
}
