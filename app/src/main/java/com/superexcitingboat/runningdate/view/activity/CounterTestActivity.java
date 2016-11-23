package com.superexcitingboat.runningdate.view.activity;

import android.hardware.Sensor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.superexcitingboat.runningdate.R;
import com.superexcitingboat.runningdate.utils.Counter.StepDetector;
import com.superexcitingboat.runningdate.utils.Counter.StepListener;
import com.superexcitingboat.runningdate.utils.Counter.StepRecorder;
import com.superexcitingboat.runningdate.utils.TimeRecorder;

public class CounterTestActivity extends AppCompatActivity implements StepRecorder.OnStepChangeListener, View.OnClickListener {
    private static final String TAG = "CounterTestActivity";
    private TextView stepCount;
    private TextView stepSum;
    private TextView maxAccelerator;
    private TextView minAccelerator;
    private Switch aSwitch;
    private int count;

    private int test = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_test);
        stepCount = (TextView) findViewById(R.id.step_count);
        stepSum = (TextView) findViewById(R.id.step_sum);
        maxAccelerator = (TextView) findViewById(R.id.accelerator_max);
        minAccelerator = (TextView) findViewById(R.id.accelerator_min);
        findViewById(R.id.accelerator_up).setOnClickListener(this);
        findViewById(R.id.accelerator_down).setOnClickListener(this);
        aSwitch = (Switch) findViewById(R.id.max_min_switch);
        stepSum.setText("sum:" + 0);
        maxAccelerator.setText("max:" + StepDetector.MAX_VALUE);
        minAccelerator.setText("min:" + StepDetector.MIN_VALUE);
        resetCount();
        findViewById(R.id.reset_step).setOnClickListener(this);
        StepRecorder.getInstance().addOnStepChangeListener(this);

        new StepListener((TextView) findViewById(R.id.native_step_count_1), Sensor.TYPE_STEP_COUNTER);
        new StepListener((TextView) findViewById(R.id.native_step_count_2), Sensor.TYPE_STEP_DETECTOR);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_step:
                Log.d(TAG, "onClick: Reset");
                TimeRecorder.getInstance().switchStatus();
                break;
            case R.id.accelerator_up:
                plus();
                break;
            case R.id.accelerator_down:
                sub();
                break;

        }
    }

    private void plus() {
        if (aSwitch.isChecked()) {
            StepDetector.MAX_VALUE += 0.5;
            maxAccelerator.setText("max:" + StepDetector.MAX_VALUE);
        } else {
            StepDetector.MIN_VALUE += 0.5;
            minAccelerator.setText("min:" + StepDetector.MIN_VALUE);
        }
    }

    private void sub() {
        if (aSwitch.isChecked()) {
            StepDetector.MAX_VALUE -= 0.5;
            maxAccelerator.setText("max:" + StepDetector.MAX_VALUE);
        } else {
            StepDetector.MIN_VALUE -= 0.5;
            minAccelerator.setText("min:" + StepDetector.MIN_VALUE);
        }
    }

    private void resetCount() {
        count = 0;
        stepCount.setText("count:" + count);
    }

    @Override
    public void onStepChange(int step) {
        count++;
        stepCount.setText("count:" + count);
        stepSum.setText("sum:" + step);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StepRecorder.getInstance().removeOnStepChangeListener(this);
    }

}
