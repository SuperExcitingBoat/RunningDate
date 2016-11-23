package com.superexcitingboat.runningdate.utils.Counter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

public class StepListener implements SensorEventListener {
    private TextView textView;
    private int type;
    private int count;

    public StepListener(TextView textView, int type) {
        this.textView = textView;
        this.type = type;
        SensorManager sensorManager = (SensorManager) textView.getContext().getSystemService(Context.SENSOR_SERVICE);
        if (type == Sensor.TYPE_STEP_COUNTER) {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                    SensorManager.SENSOR_DELAY_FASTEST);
        } else if (type == Sensor.TYPE_STEP_DETECTOR) {
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR),
                    SensorManager.SENSOR_DELAY_FASTEST);
        }
        count = 0;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_COUNTER && type == Sensor.TYPE_STEP_COUNTER) {
            textView.setText("TYPE_STEP_COUNTER:" + count++);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR && type == Sensor.TYPE_STEP_DETECTOR) {
            textView.setText("TYPE_STEP_DETECTOR:" + count++);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
