package com.superexcitingboat.runningdate.utils.Counter;

import android.hardware.SensorEventListener;


public interface TypedSensorEventListener extends SensorEventListener {
    int getSensorType();

    void setOnStepChangeListener(OnStepChangeListener onStepChangeListener);

    void removeOnStepChangeListener();
}
