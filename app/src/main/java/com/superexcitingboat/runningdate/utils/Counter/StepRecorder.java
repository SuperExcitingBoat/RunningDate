package com.superexcitingboat.runningdate.utils.Counter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import java.util.ArrayList;

public class StepRecorder implements OnStepChangeListener {
    private static final String TAG = "StepRecorder";
    private int count;
    private Context context;
    private TypedSensorEventListener typedSensorEventListener;
    private SensorManager sensorManager;
    private static StepRecorder mInstance = new StepRecorder();
    private ArrayList<OnStepChangeListener> onStepChangeListeners;

    private StepRecorder() {
    }

    public static StepRecorder getInstance() {
        return mInstance;
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
        this.context = context;
        typedSensorEventListener = new StepDetector();//一行代码替换计步算法，策略模式
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensorManager.registerListener(typedSensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_FASTEST);
        typedSensorEventListener.setOnStepChangeListener(this);
        onStepChangeListeners = new ArrayList<>();
    }


    @Override
    public void onStepChange(int step) {
        count = step;
        Log.d(TAG, "onStepChange: CurrentStep: " + step);
        for (OnStepChangeListener onStepChangeListener : onStepChangeListeners) {
            onStepChangeListener.onStepChange(count);
        }
    }
}
